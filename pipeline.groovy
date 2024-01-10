node('maven') {
    timestamps {
        wrap([$class: 'BuildUser']) {
            currentBuild.description = "User: ${env.BUILD_USER}"
        }

        def params = readYaml text: env.YAML_CONFIG ?: [:]
        params.each {
            k, v -> env.setProperty(k, v)
        }

        stage("Checkout") {
            checkout scm
        }

        Map<String, Run> jobs = [:]
        env.TEST_TYPE.tokenize(',').each { String type ->
            jobs[type] = {
                node('maven') {
                    stage("Running $type tests") {
                        build(job: "$type-tests", parameters: [text(name: "YAML_CONFIG", value: env.YAML_CONFIG)])
                    }
                }
            }
        }
        parallel jobs

        stage("Allure report") {
            env.TEST_TYPE.tokenize(',').each { String type ->
                print(jobs[type])
                copyArtifacts filter: "**/allure-results.tar.gz",
                        projectName: "$type-tests",
                        selector: specific(jobs[type].getNumber() as String),
                        optional: true
                sh "tar -xvf allure-results.tar.gz -C ./allure-results"
            }
            allure([
                    results: [[path: 'allure-results']],
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS'
            ])
        }
    }
}