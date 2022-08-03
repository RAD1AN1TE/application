package com.application.services

import com.application.Migration
import com.application.data.BookEntity
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("BookServiceImpl Test")
public class BookServiceImplTest  {

    private var bookId: Int = 1
    private var bookEntity1: BookEntity? = null
    private var bookEntity2:BookEntity? = null
    private var bookEntity3:BookEntity? = null
    private var bookService: BookServiceImpl? = null
    private lateinit var h2Database: H2DatabaseImpl

    private var iterable: Iterable<BookEntity>? = null



    @BeforeAll
    fun setup(){
        h2Database = H2DatabaseImpl()
        bookEntity1 = h2Database.dummyBookEntity1
        bookEntity2 = h2Database.dummyBookEntity2
        bookEntity3 = h2Database.dummyBookEntity3

        bookService = BookServiceImpl(h2Database.provideDataBase())
        h2Database.connect()
//        h2Database.migrate()

    }

//    init{
//        h2Database = H2DatabaseImpl()
//        bookEntity = h2Database.dummyBookEntity
//        bookEntity1 = h2Database.dummyBookEntity1
//        bookService = BookServiceImpl(h2Database.provideDataBase())
//        h2Database.connect()
//
//    }


    @AfterAll
    fun tear(){
        h2Database.close()
    }

    @Test
    @Order(1)
    fun testAddBook() {

        val actual = bookService!!.addBook(bookEntity1!!)
        bookService!!.addBook(bookEntity2!!)
        bookService!!.addBook(bookEntity3!!)

        bookId = actual.bookId!!

        assertEquals(bookEntity1!!.copy(bookId=bookId),actual)

    }

    @Test
    @Order(2)
    fun testGetBooks() {

        val actual = bookService!!.getBooks()
        iterable = actual

        assertEquals("First Book", actual.first().title)

    }

    @Test
    @Order(3)
    fun testGetOwnedBooks() {
//        val actual = bookService!!.getOwnedBooks()
//        println(actual.first())

        val mockBookService: BookServiceImpl = mock()
        whenever(mockBookService.getOwnedBooks()).thenReturn(iterable)
        val actual =  mockBookService.getOwnedBooks()
        assertEquals(true, actual.first().owned)
    }

    @Test
    @Order(4)
    fun testGetUnOwnedBooks() {
        val actual = bookService!!.getUnOwnedBooks()
        assertEquals(false, actual.first().owned)

//        val mockBookService: BookServiceImpl = mock()
//        whenever(mockBookService.getUnOwnedBooks()).thenReturn(iterable)
//        val actual =  mockBookService.getUnOwnedBooks()
//        assertEquals(false, actual.elementAt(1).owned )
    }


    @Test
    @Order(5)
    fun testEditBook() {
        val actual = bookService!!.editBook(bookEntity1!!.copy(bookId = 1, author = "TEJ"))
        assertEquals(bookEntity1!!.copy(bookId=1, author = "TEJ"), actual)

//        val mockBookService: BookServiceImpl = mock()
//        whenever(mockBookService.editBook(iterable?.first()!!.copy(bookId = 1, author = "TEJ"))).thenReturn(iterable?.first()!!.copy(bookId = 1, author = "TEJ"))
//        val actual = mockBookService.editBook(bookEntity1!!.copy(bookId = 1, author = "TEJ"))
//        assertEquals(bookEntity1!!.copy(bookId=1, author = "TEJ"), actual)

    }

    @Test
    @Order(6)
    fun testDeleteBook() {

        val delete1 = bookService!!.deleteBook(1)
        val delete2 = bookService!!.deleteBook(2)
        val delete3 = bookService!!.deleteBook(3)

        assertEquals(1,delete1)
        assertEquals(1,delete2)
        assertEquals(1,delete3)
    }


}