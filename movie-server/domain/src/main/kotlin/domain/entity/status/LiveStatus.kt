package domain.entity.status

import domain.entity.Entity
import domain.entity.ValueClass


data class LiveStatus(
    override val id: Int = -1,
    val userId: String = "",
    val score: Int = 0,
) : Entity {

}

enum class Authorities {
    USER
}

data class UserId(override val value: String) : ValueClass<String> {}
//
//open class Password(override val value: String) : ValueClass<String> {
//    override fun toString(): String {
//        return "Password()"
//    }
//}
