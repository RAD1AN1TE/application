package com.application.services


import com.application.data.BookEntity
import com.application.data.Books
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import mu.KLogger
import mu.KotlinLogging
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.DriverManager
import javax.sql.DataSource


interface H2Database{
    fun connect()
    fun migrate()
    fun close()
}

object SchemaDefinition {
    fun createSchema(database: Database) {
        transaction(database) {
            SchemaUtils.create(Books)
        }
    }
}

class H2DatabaseImpl: H2Database {


    val dummyBookEntity1 = BookEntity(

        1,
        "First Book",
        "Phoenix",
        true,

    )
    val dummyBookEntity2 = BookEntity(

        2,
        "Second Book",
        "Phoenix1",
        false,

    )
    val dummyBookEntity3 = BookEntity(
        3,
        "Third Book",
        "Phoenix3",
        true,
    )
//    var list : MutableList<BookEntity>? = null
//    list?.add(dummyBookEntity1)


    lateinit var source:HikariDataSource

    override fun connect() {
        var database = provideDataBase()
        SchemaDefinition.createSchema(database)
    }

    private fun createDataSource(): DataSource {

        val config = HikariConfig()

        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:~/test029;MODE=PostgreSQL"
        config.username = "user"
        config.password = "password"

        config.validate()
        source = HikariDataSource(config)

        return source
    }

    fun provideDataBase(): Database{
        return Database.connect(createDataSource())
    }


    override fun close() {
        source.close()
    }

    private val url: String = "jdbc:h2:~/test029;MODE=PostgreSQL"
    private val user: String = "user"
    private val password: String = "password"
    private val logger: KLogger = KotlinLogging.logger {}

    override fun migrate() {
        logger.info("Running migration...")
        liquibaseMigrate(url, user, password)
        logger.info("Migration executed successfully.")
    }


    private fun liquibaseMigrate(url: String, user: String, password: String){
        val connection = DriverManager.getConnection(url, user, password)

        try{
            val database: liquibase.database.Database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
                JdbcConnection(connection)
            )
            val liquibase = Liquibase("db/liquibase-changelog-master.xml", ClassLoaderResourceAccessor(), database)
            liquibase.update(Contexts())
        } finally {
            if (connection != null) {
                connection.rollback()
                connection.close()
            }
        }
    }


}