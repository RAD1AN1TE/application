package com.application.services

import com.application.DbConnection
import com.application.data.BookEntity
import com.application.data.Books
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import javax.sql.DataSource

open class DummyData {

    val dummyBookEntity = BookEntity(
        1,
        "Hello",
        "Phoenix",
        "Fiction",
        "123-456",
        true,
        "Very good book"
    )

    fun initDB(database: Database){

        transaction(database) {
            SchemaUtils.createMissingTablesAndColumns(Books)
        }

    }
    fun createDataSource(): DataSource {
        val config = HikariConfig()

        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = "jdbc:postgresql://localhost:5432/books"
        config.username = "user"
        config.password = "password"

        config.validate()

        return HikariDataSource(config)
    }

    fun provideDatabase( ds: DataSource): Database {
        return Database.connect(ds)
    }
}