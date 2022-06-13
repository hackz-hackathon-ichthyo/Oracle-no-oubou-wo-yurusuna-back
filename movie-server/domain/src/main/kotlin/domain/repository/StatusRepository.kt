package domain.repository

import domain.entity.status.LiveStatus
import domain.entity.status.UserId

//TODOep
interface StatusRository : Repository<LiveStatus, Int> {
    suspend fun findByUserId(id: UserId): LiveStatus?
}
