package com.assignment.libraryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.libraryapp.data.local.dao.BookDao
import com.assignment.libraryapp.data.local.entity.BookEntity

@Database(
    version = 1,
    entities = [BookEntity::class]
)
abstract class BookDatabase: RoomDatabase() {

    abstract val dao: BookDao

    companion object {
        const val name = "book_db"
    }
}