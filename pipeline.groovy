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
            jobs['android'] = getTestRunJob("mvn test")
        }
        if (env.TEST_TYPE == 'ui') {
            jobs['ui'] = getTestRunJob("mvn test -P ${env.BROWSER_NAME}")
        }
        if (env.TEST_TYPE == 'api') {
            jobs['api'] = getTestRunJob("mvn test")
        }
        if (env.TEST_TYPE == 'wiremock') {
            jobs['wiremock'] = getTestRunJob("mvn test -P ${env.STUB_TYPE}")
        }

        stage("Checkout") {
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

Closure getTestRunJob(String mvn) {
    return {
        sh script: "git checkout -b ${env.TEST_BRANCH} origin/${env.TEST_BRANCH}"
        def status = sh script: mvn, returnStatus: true
        if (status == 1) {
            currentBuild.result = "UNSTABLE"
        }
    }
}