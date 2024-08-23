package com.assignment.libraryapp.ui.screen.book

import java.util.Date

data class BookState(
    val id: Int? = null,
    val title: String = "",
    val author: String = "",
    val publishingDate: String = "",
    val isbn: String = "",
    val description: String = "",
    val path: String = ""
)