package usecases.model

import domain.entity.status.LiveStatus

data class StatusModel(
    val id: Int,
    val userId: String,
    val score: Int,
) {
    constructor(status: LiveStatus) : this(status.id, status.userId, status.score)
}

