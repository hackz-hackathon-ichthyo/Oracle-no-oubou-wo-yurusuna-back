package tech.notchman.infra

import kotlinx.serialization.Serializable
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.core.mapper.reflect.ColumnName
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

@Serializable
data class Channel(
    @ColumnName("user_id") val id: String, @ColumnName("url") val url: String, @ColumnName("secret") val secret: String

)

val jdbi = Jdbi.create("jdbc:postgresql://db:5432/oracle", "oracle", "oracle")
    .installPlugin(SqlObjectPlugin())
    .installPlugin(KotlinPlugin())

interface ChannelDao {
    @SqlQuery("SELECT * FROM channels where user_id=:id limit 1")
    fun getRecord(@Bind("id") id: String): Channel

    @SqlQuery("SELECT count(*) FROM channels where user_id=:id")
    fun checkRecord(@Bind("id") id: String): Int

    @SqlUpdate("insert into channels(id,user_id,secret,url) values(:id,:user_id,:secret,:url)")
    fun makeRecord(
        @Bind("id") id: String,
        @Bind("user_id") user_id: String,
        @Bind("secret") secret: String,
        @Bind("url") url: String
    )
}