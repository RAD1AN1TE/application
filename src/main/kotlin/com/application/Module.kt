package com.application

import com.application.services.BookService
import com.application.services.BookServiceImpl
import org.koin.dsl.module


val booksModule = module{

    single{DbConnection.dataBase}
    single(createdAtStart = true){Migration}
    single<BookService>{ BookServiceImpl(get()) }

}