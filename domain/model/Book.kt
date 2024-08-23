package com.assignment.libraryapp.domain.model

data class Book(
    val id: Int? = null,
    val title: String = "",
    val author: String = "",
    val publishingDate: String = "",
    val isbn: String = "",
    val description: String = "",
    val path: String = ""
)