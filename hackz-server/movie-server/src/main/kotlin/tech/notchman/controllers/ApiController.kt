package tech.notchman.controllers

import tech.notchman.infra.ApiClient

class ApiController(private val apiClient: ApiClient) {
    
    fun getProgress(): Float {
        val issues = apiClient.getIssues()
        val len = issues.issues.size
        var count = 0.0f
        for (issue in issues.issues) {
            if (issue.state != "open") {
                count += 1.0f
            }
        }
        return count / len
    }
}