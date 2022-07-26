package com.application.services
//
//import com.application.data.Book
//import com.application.data.Books
//import org.jetbrains.exposed.sql.Database
//import org.jetbrains.exposed.sql.ResultRow
//import org.junit.jupiter.api.Assertions.assertAll
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//
//import org.junit.jupiter.api.BeforeAll
//import org.junit.jupiter.api.BeforeEach
////import org.junit.jupiter.api.assertAll
//import org.mockito.Mock
//import org.mockito
//
//
//
//internal class BookServiceImplTest {
//
////    private var mockBookService: BookService? = null
////
////    @BeforeAll
////    fun beforeAll(){
////        mockBookService = Mockito.mock( BookService::class.java)
////    }
//
//    @Mock
//    private var mockBookService : BookService? = null
//    @Mock
//    private var database : Database? = null
//    @Mock
//    private var book : Book? = null
//    @Mock
//    private var row : ResultRow? = null
//
//
//    List<>
//    @BeforeAll
//    fun setUp(){
//
//        when(book!!.bookId).thenReturn(1)
//        when(book!!.title).thenReturn("valorant")
//        when(book!!.author).thenReturn("jett")
//        when(book!!.genre).thenReturn("fiction")
//        when(book!!.isbn).thenReturn("123-456")
//        when(book!!.owned).thenReturn(true)
//        when(book!!.about).thenReturn("good book on science")
//
//        when(row!![Books.bookId]).thenReturn("1")
//        when(row!![Books.title]).thenReturn("valorant")
//        when(row!![Books.author]).thenReturn("jett")
//        when(row!![Books.genre]).thenReturn("fiction")
//        when(row!![Books.isbn]).thenReturn("123-456")
//        when(row!![Books.owned]).thenReturn(true)
//        when(row!![Books.about]).thenReturn("good book on science")
//    }
//
//
//    @Test
//    fun testToBooks(){
//        var books : Book? = null
//        books?.bookId = 1
//        books?.title = "valorant"
//        books?.author = "jett"
//        books?.genre =  "fiction"
//        books?.isbn = "123-456"
//        books?.owned = true
//        books?.about = "good book on science"
//
//        assertAll(
//
//
//        )
//        assertEquals(books!!.bookId, row!![Books.bookId])
//        assertEquals(books!!.title, row!![Books.title])
//        assertEquals(books!!.author, row!![Books.author])
//        assertEquals(books!!.genre, row!![Books.genre])
//        assertEquals(books!!.isbn, row!![Books.isbn])
//        assertEquals(books!!.owned, row!![Books.owned])
//        assertEquals(books!!.about, row!![Books.about])
//
//
//
//        println("Success")
//
//    }
//
////    @Test
////    fun getBooks() {
////        var books = Books()
////    }
//
//    @Test
//    fun getOwnedBooks() {
//
//    }
//
//    @Test
//    fun getUnOwnedBooks() {
//
//    }
//
//    @Test
//    fun addBook() {
//
//    }
//
//    @Test
//    fun editBook() {
//
//    }
//
//    @Test
//    fun deleteBook() {
//
//    }
//
//}