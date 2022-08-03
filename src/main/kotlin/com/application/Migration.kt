package com.application


import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.*
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import mu.KLogger
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import java.sql.DriverManager

object Migration: KoinComponent{

    private val url: String = "jdbc:postgresql://localhost:5432/books"
    private val user: String = "user"
    private val password: String = "password"
    private val logger: KLogger = KotlinLogging.logger {}

    init{
        migrate()
    }

    private fun migrate(){
        logger.info("Running migration...")
        liquibaseMigrate(url, user, password)
        logger.info("Migration executed successfully.")
    }



    private fun liquibaseMigrate(url: String, user: String, password: String){
        val connection = DriverManager.getConnection(url, user, password)

        try{
            val database: Database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(connection))
            val liquibase = Liquibase("db/liquibase-changelog-master.xml", ClassLoaderResourceAccessor(), database)
            liquibase.update(Contexts())
        }
//        catch(){
//            println("Catch block executed...")
//        }
        finally {
                if (connection != null) {
                    connection.rollback()
                    connection.close()
                }
            }
        }
    }


