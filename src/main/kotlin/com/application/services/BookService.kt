package com.application.services

import com.application.data.Book
import com.application.data.BookEntity


interface BookService {

    fun getBooks():Iterable<BookEntity>
    fun getOwnedBooks(): Iterable<BookEntity>
    fun getUnOwnedBooks(): Iterable<BookEntity>
    fun editBook( book: BookEntity ): BookEntity
    fun addBook(book: BookEntity): BookEntity
    fun deleteBook(bookId: Int) : Int

}