node('maven') {
    timestamps {
        wrap([$class: 'BuildUser']) {
            currentBuild.description = "USER: ${env.BUILD_USER}"
        }

        stage("Checkout") {
            checkout scm
        }
        stage("Running tests") {
            def status = sh script: "mvn test", returnStatus: true
            if (status == 1) {
                currentBuild.result = "UNSTABLE"
            }
        }
        stage("Allure report") {
            sh "tar -czf allure-results.tar.gz target/allure-results"
            sh "tar -czf surefire-reports.tar.gz target/surefire-reports"
            archiveArtifacts artifacts: "*.tar.gz",
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
                               |JOB: ${env.JOB_NAME}
                               |${currentBuild.description}
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