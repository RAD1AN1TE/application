package com.application.services

import com.application.data.Book
import com.application.data.Books
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction



class BookServiceImpl(private val db: Database) : BookService {



    private fun toBook(row: ResultRow) = Book(
        bookId = row[Books.bookId],
        title = row[Books.title],
        author = row[Books.author],
        genre = row[Books.genre],
        isbn = row[Books.isbn],
        owned = row[Books.owned],
        about = row[Books.about]
    )


    override suspend fun getBooks(): Iterable<Book> = transaction(db){

        Books.selectAll().map(::toBook)

    }

    override suspend fun getOwnedBooks(): Iterable<Book> = transaction(db) {

        Books.select{  Books.owned eq true }.map(::toBook)

    }

    override suspend fun getUnOwnedBooks(): Iterable<Book> = transaction(db) {

        Books.select{  Books.owned eq false }.map(::toBook)

    }


    override suspend fun addBook(book: Book): Unit = transaction(db){

        Books.insert{
            it[this.title] = book.title
            it[this.author] = book.author
            it[this.genre] = book.genre
            it[this.owned] = book.owned
            it[this.isbn] = book.isbn
            it[this.about] = book.about

        }
    }

    override suspend fun editBook(bookId:Int, book: Book): Int = transaction(db) {

       Books.update({Books.bookId eq bookId}){
           it[this.title] = book.title
           it[this.author]  = book.author
           it[this.owned] = book.owned

       }
    }

    override suspend fun deleteBook(bookId:Int) : Int = transaction(db) {

        Books.deleteWhere { Books.bookId eq bookId }

    }

}




