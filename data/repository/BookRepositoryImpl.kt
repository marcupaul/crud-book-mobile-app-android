package com.assignment.libraryapp.data.repository

import com.assignment.libraryapp.data.local.dao.BookDao
import com.assignment.libraryapp.data.mapper.asExternalModel
import com.assignment.libraryapp.data.mapper.toEntity
import com.assignment.libraryapp.domain.model.Book
import com.assignment.libraryapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val dao: BookDao
) : BookRepository {

    override fun getAllBooks(): Flow<List<Book>> {
        return dao.getAllBooks()
            .map { books ->
                books.map {
                    it.asExternalModel()
                }
            }
    }

    override suspend fun getBookById(id: Int): Book? {
        return dao.getBookById(id)?.asExternalModel()
    }

    override suspend fun insertBook(book: Book) {
        dao.insertBook(book.toEntity())
    }

    override suspend fun deleteBook(book: Book) {
        dao.deleteBook(book.toEntity())
    }

    override suspend fun updateBook(book: Book) {
        dao.updateBook(book.toEntity())
    }
}