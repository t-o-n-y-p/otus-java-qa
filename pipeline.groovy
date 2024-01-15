node('maven') {
    timestamps {
        wrap([$class: 'BuildUser']) {
            currentBuild.description = "User: ${env.BUILD_USER}\n${env.YAML_CONFIG}"
        }

        def params = readYaml text: env.YAML_CONFIG ?: [:]
        params.each {
            k, v -> env.setProperty(k, v)
        }

        stage("Checkout") {
            checkout scm
        }

        def jobs = [:]
        def runningJobs = []
        env.TEST_TYPE.tokenize(',').each { String type ->
            jobs[type] = {
                node('maven') {
                    stage("Running $type tests") {
                        runningJobs.add(
                                build(
                                        job: "$type-tests",
                                        propagate: false,
                                        parameters: [text(name: "YAML_CONFIG", value: env.YAML_CONFIG)]
                                )
                        )
                    }
                }
            }
        }
        parallel jobs

        stage("Allure report") {
            runningJobs.each { job ->
                copyArtifacts filter: "*.tar.gz",
                        projectName: job.getProjectName(),
                        selector: specific("${job.getNumber()}"),
                        optional: true
                sh "tar -xvf allure-results.tar.gz --one-top-level=allure-results --strip-components=2"
                sh "tar -xvf surefire-reports.tar.gz --one-top-level=surefire-reports --strip-components=2"
            }
            allure(
                    results: [[path: 'allure-results']],
                    disabled: false,
                    reportBuildPolicy: 'ALWAYS'
            )
            def summary = junit testResults: "surefire-reports/*.xml"
            if (summary.totalCount != summary.passCount) {
                currentBuild.result = 'UNSTABLE'
            }
        }
    }
}