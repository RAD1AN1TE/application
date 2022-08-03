package com.application.services

import com.application.data.Book
import com.application.data.BookEntity
import com.application.data.Books
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction



class BookServiceImpl(private val db: Database) : BookService {


    private fun toBook(row: ResultRow) = BookEntity(
        bookId = row[Books.bookId],
        title = row[Books.title],
        author = row[Books.author],
//        genre = row[Books.genre],
//        isbn = row[Books.isbn],
        owned = row[Books.owned]
//        about = row[Books.about]
    )


    override fun getBooks(): Iterable<BookEntity> = transaction(db){

        Books.selectAll().orderBy((Books.columns.find {
            it.name == "bookId"
        } ?: Books.bookId) to SortOrder.ASC).map(::toBook)

    }

    override fun getOwnedBooks(): Iterable<BookEntity> = transaction(db) {

        Books.select{  Books.owned eq true }.orderBy((Books.columns.find {
            it.name == "bookId"
        } ?: Books.bookId) to SortOrder.ASC).map(::toBook)

    }

    override fun getUnOwnedBooks(): Iterable<BookEntity> = transaction(db) {

        Books.select{  Books.owned eq false }.orderBy((Books.columns.find {
            it.name == "bookId"
        } ?: Books.bookId) to SortOrder.ASC).map(::toBook)

    }


    override fun addBook(book: BookEntity): BookEntity = transaction(db){

        Books.insert{
            it[this.bookId] = book.bookId
            it[this.title] = book.title
            it[this.author] = book.author
            it[this.owned] = book.owned

        }
        book
    }

    override fun editBook(book: BookEntity): BookEntity = transaction(db) {

       Books.update({Books.bookId eq book.bookId!!}){
           it[title] = book.title
           it[author]  = book.author
           it[owned] = book.owned
       }

        book
    }

    override fun deleteBook(bookId:Int) : Int = transaction(db) {

        Books.deleteWhere { Books.bookId eq bookId }

    }

}




