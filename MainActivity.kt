package com.assignment.libraryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assignment.libraryapp.ui.screen.book.BookScreen
import com.assignment.libraryapp.ui.screen.book.BookViewModel
import com.assignment.libraryapp.ui.screen.book_list.BookListScreen
import com.assignment.libraryapp.ui.screen.book_list.BookListViewModel
import com.assignment.libraryapp.ui.theme.LibraryAppTheme
import com.assignment.libraryapp.ui.util.Route
import com.assignment.libraryapp.ui.util.UiEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Route.bookList
                ) {
                    composable(route = Route.bookList) {
                        val viewModel = hiltViewModel<BookListViewModel>()
                        val bookList by viewModel.bookList.collectAsStateWithLifecycle()

                        BookListScreen(
                            bookList = bookList,
                            onBookClick = {
                                navController.navigate(
                                    Route.book.replace(
                                        "{id}",
                                        it.id.toString()
                                    )
                                )
                            },
                            onAddBookClick = {
                                navController.navigate(Route.book)
                            }
                        )
                    }

                    composable(route = Route.book) {
                        val viewModel = hiltViewModel<BookViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = true) {
                            viewModel.event.collect { event ->
                                when (event) {
                                    is UiEvent.NavigateBack -> {
                                        navController.popBackStack()
                                    }

                                    else -> Unit
                                }
                            }
                        }

                        BookScreen(
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                }
            }
        }
    }
}