package com.assignment.libraryapp.data.di

import android.content.Context
import androidx.room.Room
import com.assignment.libraryapp.data.local.BookDatabase
import com.assignment.libraryapp.data.repository.BookRepositoryImpl
import com.assignment.libraryapp.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookDatabase(@ApplicationContext context: Context): BookDatabase =
        Room.databaseBuilder(
            context,
            BookDatabase::class.java,
            BookDatabase.name
        ).build()

    @Provides
    @Singleton
    fun provideBookRepository(database: BookDatabase): BookRepository =
        BookRepositoryImpl(dao = database.dao)
}