package com.assignment.libraryapp.ui.screen.book

sealed interface BookEvent {
    data class TitleChange(val value: String): BookEvent
    data class AuthorChange(val value: String): BookEvent
    data class PublishingDateChange(val value: String): BookEvent
    data class IsbnChange(val value: String): BookEvent
    data class DescriptionChange(val value: String): BookEvent
    data class PathChange(val value: String): BookEvent
    object Save : BookEvent
    object NavigateBack : BookEvent
    object DeleteBook : BookEvent
}