package domain.repository

import domain.entity.status.LiveStatus
import domain.entity.status.UserId

//TODOep
interface StatusRepository : Repository<LiveStatus, Int> {
    suspend fun findByUserId(id: UserId): LiveStatus?
}
