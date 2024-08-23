package com.assignment.libraryapp.data.mapper

import com.assignment.libraryapp.data.local.entity.BookEntity
import com.assignment.libraryapp.domain.model.Book

fun BookEntity.asExternalModel(): Book = Book(
    id, title, author, publishingDate, isbn, description, path
)

fun Book.toEntity(): BookEntity = BookEntity(
    id, title, author, publishingDate, isbn, description, path
)