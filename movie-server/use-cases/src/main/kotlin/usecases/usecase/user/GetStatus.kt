package usecases.usecase.user

import domain.entity.status.UserId
import domain.entity.user.Authorities
import domain.repository.StatusRepository
import usecases.dependency.Logger
import usecases.model.StatusModel
import usecases.model.UserModel
import usecases.usecase.Query
import usecases.usecase.UsecaseA1
import kotlin.reflect.typeOf

@Query
class GetStatus(
    logger: Logger, private val repository: StatusRepository
) : UsecaseA1<String, StatusModel>(typeOf<String>(), typeOf<StatusModel>(), logger) {
    override val authenticated = false
    override val authorities = emptyList<Authorities>()
    override suspend fun executor(authentication: UserModel?, a0: String): StatusModel {
        return repository.findByUserId(UserId(a0))?.let { StatusModel(it) }!!
    }
}
