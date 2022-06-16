package tech.notchman.infra

data class Issue(
    val id: Int, val state: String
)

data class Issues(
    val issues: List<Issue>
)
