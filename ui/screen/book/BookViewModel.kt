package com.assignment.libraryapp.ui.screen.book

import android.R
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.libraryapp.domain.model.Book
import com.assignment.libraryapp.domain.repository.BookRepository
import com.assignment.libraryapp.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookViewModel @Inject constructor(
    private val repository: BookRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(BookState())
    val state = _state.asStateFlow()

    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

    init {
        savedStateHandle.get<String>("id")?.let {
            val id = it.toInt()
            viewModelScope.launch {
                repository.getBookById(id)?.let { book ->
                    _state.update { screenState ->
                        screenState.copy(
                            id = book.id,
                            title = book.title,
                            author = book.author,
                            publishingDate = book.publishingDate,
                            isbn = book.isbn,
                            description = book.description,
                            path = book.path
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: BookEvent) {
        when (event) {
            is BookEvent.TitleChange -> {
                _state.update {
                    it.copy(
                        title = event.value
                    )
                }
            }

            is BookEvent.AuthorChange -> {
                _state.update {
                    it.copy(
                        author = event.value
                    )
                }
            }

            is BookEvent.PublishingDateChange -> {
                _state.update {
                    it.copy(
                        publishingDate = event.value
                    )
                }
            }

            is BookEvent.IsbnChange -> {
                _state.update {
                    it.copy(
                        isbn = event.value
                    )
                }
            }

            is BookEvent.DescriptionChange -> {
                _state.update {
                    it.copy(
                        description = event.value
                    )
                }
            }

            is BookEvent.PathChange -> {
                _state.update {
                    it.copy(
                        path = event.value
                    )
                }
            }

            BookEvent.NavigateBack -> sendEvent(UiEvent.NavigateBack)
            BookEvent.Save -> {
                viewModelScope.launch {
                    val state = state.value
                    val note = Book(
                        id = state.id,
                        title = state.title,
                        author = state.author,
                        publishingDate = state.publishingDate,
                        isbn = state.isbn,
                        description = state.description,
                        path = state.path
                    )
                    if (state.id == null) {
                        repository.insertBook(note)
                    } else {
                        repository.updateBook(note)
                    }
                    sendEvent(UiEvent.NavigateBack)
                }
            }

            BookEvent.DeleteBook -> {
                viewModelScope.launch {
                    val state = state.value
                    val note = Book(
                        id = state.id,
                        title = state.title,
                        author = state.author,
                        publishingDate = state.publishingDate,
                        isbn = state.isbn,
                        description = state.description,
                        path = state.path
                    )
                    repository.deleteBook(note)
                }
                sendEvent(UiEvent.NavigateBack)
            }
        }
    }
}