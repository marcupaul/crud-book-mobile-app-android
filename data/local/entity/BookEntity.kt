package com.assignment.libraryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey val id: Int?,
    val title: String,
    val author: String,
    val publishingDate: String,
    val isbn: String,
    val description: String,
    val path: String
)