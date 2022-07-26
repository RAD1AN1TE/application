package com.application.routes

import com.application.data.Book
import com.application.services.BookService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject



fun Application.bookRoutes(){

    val bookService: BookService by inject()

    routing{
        route("/api"){
            get("books"){
                val allBooks = bookService.getBooks()
                call.respond(allBooks)
            }

            get("books/own"){
                val ownBooks = bookService.getOwnedBooks()
                call.respond(ownBooks)
            }

            get("books/un-own"){
                val unOwnBooks = bookService.getUnOwnedBooks()
                call.respond(unOwnBooks)
            }

            post("book/new"){
                val bookRequest = call.receive<Book>()
                bookService.addBook(bookRequest)
                call.respond(HttpStatusCode.Accepted)
            }

            post("book/{id}/edit"){
                val bookId = call.parameters["id"]?.toIntOrNull()?:throw NotFoundException()
                val bookRequest = call.receive<Book>()
                bookService.editBook(bookId, bookRequest)
                call.respond(HttpStatusCode.Accepted)
            }

            delete("book/{id}"){
                val bookId = call.parameters["id"]?.toIntOrNull()?:throw NotFoundException()
                bookService.deleteBook(bookId)
                call.respond(HttpStatusCode.OK)
            }
        }
    }


}