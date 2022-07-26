package com.application.data

import org.jetbrains.exposed.sql.Table

object Books: Table("books"){

    val bookId = integer("bookId").autoIncrement()
    val title = varchar("title", 250)
    val author = varchar("author", 50)
    val genre = varchar("genre", 20)
    val isbn = varchar("isbn",  50)
    val owned = bool("owned")
    val about = text("about")

    override val primaryKey = PrimaryKey(bookId)

}

data class Book(

    var bookId: Int,
    var title: String,
    var author: String,
    var genre: String,
    var isbn: String,
    var owned: Boolean,
    var about: String,

    )
