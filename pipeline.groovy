node('maven') {
    timestamps {
        wrap([$class: 'BuildUser']) {
            currentBuild.description = "User: ${env.BUILD_USER}"
        }

        def params = readYaml text: env.YAML_CONFIG ?: [:]
        params.each {
            k, v -> env.setProperty(k, v)
        }

        def jobs = [:]
        if (env.TEST_TYPE == 'android') {
            jobs['android'] = {
                def status = sh script: "mvn test", returnStatus: true
                if (status == 1) {
                    currentBuild.result = "UNSTABLE"
                }
            }
        }
        if (env.TEST_TYPE == 'ui') {
            jobs['ui'] = {
                def status = sh script: "mvn test -P ${env.BROWSER_NAME}", returnStatus: true
                if (status == 1) {
                    currentBuild.result = "UNSTABLE"
                }
            }
        }
        if (env.TEST_TYPE == 'api') {
            jobs['api'] = {
                def status = sh script: "mvn test", returnStatus: true
                if (status == 1) {
                    currentBuild.result = "UNSTABLE"
                }
            }
        }
        if (env.TEST_TYPE == 'wiremock') {
            jobs['wiremock'] = {
                def status = sh script: "mvn test -P ${env.STUB_TYPE}", returnStatus: true
                if (status == 1) {
                    currentBuild.result = "UNSTABLE"
                }
            }
        }

        stage("Checkout") {
            env.setProperty('BRANCH', env.TEST_BRANCH)
            checkout scm
        }
        stage("Running tests") {
            parallel jobs
        }
        stage("Allure report") {
            allure(
                    results: [[path: "target/allure-results"]],
                    disabled: false,
                    reportBuildPolicy: 'ALWAYS'
            )
        }
        stage("Send to Telegram") {
            def summary = junit testResults: "**/target/surefire-reports/*.xml"
            String message = """Test Summary
                               |
                               |Total: ${summary.totalCount}
                               |Passed: ${summary.passCount}
                               |Failed: ${summary.failCount}
                               |Skipped: ${summary.skipCount}""".stripMargin()
            withCredentials([
                    string(credentialsId: 'botToken', variable: 'BOT_TOKEN'),
                    string(credentialsId: 'chatIds', variable: 'CHAT_IDS')
            ]) {
                env.CHAT_IDS.tokenize(',').each { chatId ->
                    httpRequest consoleLogResponseBody: true,
                            contentType: 'APPLICATION_JSON',
                            httpMode: 'POST',
                            requestBody: """{\"chat_id\":$chatId,\"text\":\"$message\",
                                                |\"reply_markup\":{\"inline_keyboard\":[[{
                                                |\"text\":\"Test report\",
                                                |\"url\":\"${env.BUILD_URL.replace('localhost', '127.0.0.1')}allure\"
                                                |}]]}}""".stripMargin(),
                            url: "https://api.telegram.org/bot${env.BOT_TOKEN}/sendMessage"
                }
            }
        }
    }
}