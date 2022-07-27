package com.application.services

import com.application.data.BookEntity
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("BookServiceImpl Test")
public class BookServiceImplTest {

    private var bookId: Int = 1
    private var bookEntity: BookEntity? = null
    private var bookService: BookServiceImpl? = null
    private var dummyData = DummyData()
    private val datasource = dummyData.createDataSource()
    private val database = dummyData.provideDatabase(datasource)



    init{
        dummyData.initDB(database)
        bookEntity = dummyData.dummyBookEntity
        bookService = BookServiceImpl(database)
    }

//    @AfterClass
//    fun afterAll(){
//        bookEntity = null
//        bookService = null
//    }

    @Test
    fun testGetBooks() {
        val actual = bookService!!.getBooks()

        assertEquals(4, actual.first().bookId)
    }

    @Test
    fun testGetOwnedBooks() {
        val actual = bookService!!.getOwnedBooks()
        assertEquals(true, actual.first().owned)
    }

    @Test
    fun getUnOwnedBooks() {
        val actual = bookService!!.getUnOwnedBooks()
        assertEquals(false, actual.first().owned)
    }

    @Test
    fun testAddBook() {
        val actual = bookService!!.addBook(bookEntity!!)
        bookId = actual.bookId!!
        assertEquals(bookEntity!!.copy(bookId=bookId),actual)

    }

//    @DisplayName("On edit it will edit the book entity in table")
//    @Order(1)
    @Test
    fun testEditBook() {
        val actual = bookService!!.editBook(bookEntity!!.copy(bookId = 1, author = "TEJ"))
        assertEquals(bookEntity!!.copy(bookId=1, author = "TEJ"), actual)
    }

    @Test
//    @DisplayName("On delete it will delete the row in table")
    suspend fun testDeleteBook() {
        val actualValue = bookService!!.deleteBook(bookId)
        assertEquals(1,actualValue)
    }
}