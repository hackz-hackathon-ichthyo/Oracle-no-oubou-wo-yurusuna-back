package tech.notchman.controllers

import tech.notchman.infra.ApiClient

class ApiController(private val apiClient: ApiClient) {

    fun getProgress(): Progress {
        val issues = apiClient.getIssues()
        val len = issues.size
        var count = 0
        for (issue in issues) {
            print(issue)
            if (issue.state != "open") {
                count += 1
            }
        }
        return Progress(progress = count.toFloat() / len.toFloat(), count = len, open_cnt = count)
    }
}