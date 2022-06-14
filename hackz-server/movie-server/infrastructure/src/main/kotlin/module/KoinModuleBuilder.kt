package module

import controllers.UserController
import org.koin.dsl.module

import repository.IUserRepository
import repository.UserRepository
import service.UserService
import service.UserServiceImpl

object KoinModuleBuilder {
    fun modules(): List<org.koin.core.module.Module> = listOf(module {
        // Controllers
        single { UserController(get()) }

        // Services
        single<UserService> { UserServiceImpl(get()) }

        // Repositories
        single<IUserRepository> { UserRepository() }
    })
}
