package com.application.services

import com.application.data.Book


interface BookService {

    suspend fun getBooks():Iterable<Book>
    suspend fun getOwnedBooks(): Iterable<Book>
    suspend fun getUnOwnedBooks(): Iterable<Book>
    suspend fun editBook(bookId: Int, book: Book): Int
    suspend fun addBook(book: Book)
    suspend fun deleteBook(bookId: Int) : Int

}