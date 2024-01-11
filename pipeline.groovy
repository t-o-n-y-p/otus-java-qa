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
        stage("Running tests") {
            def status = sh script: "mvn test -P ${env.STUB_TYPE}", returnStatus: true
            if (status == 1) {
                currentBuild.result = "UNSTABLE"
            }
        }
        stage("Allure report") {
            sh "tar -czf allure-results.tar.gz target/allure-results"
            archiveArtifacts artifacts: "**/allure-results.tar.gz",
                    allowEmptyArchive: true,
                    fingerprint: true,
                    onlyIfSuccessful: true
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