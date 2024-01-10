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

        def jobs = [:]
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
    }
}