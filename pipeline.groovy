node('maven') {
    timestamps {
        wrap([$class: 'BuildUser']) {
            currentBuild.description = "User: ${env.BUILD_USER}"
        }

        params = readYaml text: env.YAML_CONFIG ?: [:]
        params.each {
            k, v -> env.setProperty(k, v)
        }

        stage("Checkout") {
            scm checkout
        }
        stage("Running tests") {
            status = sh(
                    script: "mvn test -P ${env.BROWSER_NAME}",
                    returnStatus: true
            )
            if (status == 1) {
                currentBuild.status = "UNSTABLE"
            }
        }
        stage("Allure report") {
            allure(
                    results: ["$WORKSPACE/target/allure-results"],
                    disabled: false,
                    reportBuildPolicy: 'ALWAYS'
            )
        }
        stage("Send to Telegram") {
            summary = junit testResults: "$WORKSPACE/target/surefire-reports/TEST-*.xml"
            message = "Test Summary\n\nTotal: ${summary.totalCount}\nPassed: ${summary.passCount}\nFailed: ${summary.failCount}\nSkipped: ${summary.skipCount}"
            withCredentials([
                    string(credentialsId: 'botToken', variable: 'BOT_TOKEN'),
                    string(credentialsId: 'chatIds', variable: 'CHAT_IDS')
            ]) {
                $CHAT_IDS.tokenize(',').each {
                    chatId -> {
                        body = ['chatId': chatId]
                        httpRequest consoleLogResponseBody: true,
                                    contentType: 'APPLICATION_JSON',
                                    httpMode: 'POST',
                                    requestBody: body,
                                    url: "https://api.telegram.org/bot$BOT_TOKEN/sendMessage",
                                    validResponseCodes: '200'
                    }
                }
            }
        }
    }
}