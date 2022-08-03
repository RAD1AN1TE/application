package com.application

import com.application.data.Books
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import javax.sql.DataSource

const val HIKARI = "ktor.hikariconfig"


object DbConnection {

    val dataSource = createDataSource()
    val dataBase = provideDatabase(dataSource)

    fun createDataSource(): DataSource{
        val config = HikariConfig()

        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = "jdbc:postgresql://localhost:5432/books"
        config.username = "user"
        config.password = "password"

        config.validate()

        return HikariDataSource(config)
    }

    fun provideDatabase( ds: DataSource ): Database{
        return Database.connect(ds)
    }


}

fun Application.initDB(){

    val database = DbConnection.dataBase

    transaction(database) {
        SchemaUtils.create(Books)
    }

}