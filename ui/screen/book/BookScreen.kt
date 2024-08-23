@file:OptIn(ExperimentalMaterial3Api::class)

package com.assignment.libraryapp.ui.screen.book

import android.app.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun BookScreen(
    state: BookState,
    onEvent: (BookEvent) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(BookEvent.NavigateBack)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            val builder = AlertDialog.Builder(context)
                            builder.setMessage("Are you sure you want to delete this book?")
                                .setCancelable(false)
                                .setPositiveButton("Yes") { dialog, id ->
                                    onEvent(BookEvent.DeleteBook)
                                }
                                .setNegativeButton("No") { dialog, id ->
                                    dialog.dismiss()
                                }
                            val alert = builder.create()
                            alert.show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "delete"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(
                    horizontal = 20.dp,
                    vertical = 15.dp
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = state.title,
                onValueChange = {
                    onEvent(BookEvent.TitleChange(it))
                },
                placeholder = {
                    Text(text = "Title")
                }
            )
            OutlinedTextField(
                value = state.author,
                onValueChange = {
                    onEvent(BookEvent.AuthorChange(it))
                },
                placeholder = {
                    Text(text = "Author")
                }
            )
            OutlinedTextField(
                value = state.publishingDate,
                onValueChange = {
                    onEvent(BookEvent.PublishingDateChange(it))
                },
                placeholder = {
                    Text(text = "Publishing Date")
                }
            )
            OutlinedTextField(
                value = state.isbn,
                onValueChange = {
                    onEvent(BookEvent.IsbnChange(it))
                },
                placeholder = {
                    Text(text = "ISBN")
                }
            )
            OutlinedTextField(
                value = state.description,
                onValueChange = {
                    onEvent(BookEvent.DescriptionChange(it))
                },
                placeholder = {
                    Text(text = "Description")
                }
            )
            OutlinedTextField(
                value = state.path,
                onValueChange = {
                    onEvent(BookEvent.PathChange(it))
                },
                placeholder = {
                    Text(text = "Filepath")
                }
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        onEvent(BookEvent.Save)
                    },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}