package com.example.todoapp.ui.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import com.example.todoapp.ui.presentation.components.CustomImageButton
import com.example.todoapp.ui.presentation.task_screen.TodoViewModel
import com.example.todoapp.ui.presentation.todo.TodoEffect
import com.example.todoapp.ui.presentation.todo.TodoIntent
import com.example.todoapp.ui.extensions.toFormattedDate
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    navController: NavHostController,
    todo: TodoEntity,
    viewModel: TodoViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var showDeleteDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(todo) {
        viewModel.onIntent(TodoIntent.SetTodoForEdit(todo))
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TodoEffect.ShowToast -> {
                    if (effect.message.contains("successfully")) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        colorResource(id = R.color.bg_top_color),
                        colorResource(id = R.color.bg_bottom_color)
                    )
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            if (showDeleteDialog) {
                AlertDialog(
                    onDismissRequest = { showDeleteDialog = false },
                    title = { Text(text = "Delete Todo") },
                    text = { Text(text = "Are you sure you want to delete this task?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                coroutineScope.launch {

                                    viewModel.onIntent(TodoIntent.DeleteTodo)
                                }
                                showDeleteDialog = false
                            }
                        ) {
                            Text("Delete", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDeleteDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.back_ic),
                    "back_ic",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { navController.popBackStack() }
                )
                Text("Task Details", color = Color.White, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(13.dp),

                ) {
                TextField(
                    value = state.title,
                    onValueChange = {
                        coroutineScope.launch {

                            viewModel.onIntent(TodoIntent.TitleChanged(it))
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 20.dp),
                    placeholder = {
                        Text(
                            "Task title",
                            color = Color.White.copy(alpha = 0.5f),
                            fontWeight = FontWeight.Bold
                        )
                    }, maxLines = 1,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    )
                )
                Image(
                    painter = painterResource(R.drawable.edit_ic),
                    "",
                    modifier = Modifier.padding(top = 20.dp, end = 15.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(13.dp),

                ) {
                Spacer(modifier = Modifier.padding(start = 10.dp, top = 0.dp))
                Image(painter = painterResource(R.drawable.calender_ic), "calender")

                Text(
                    "${state.selectedDate.toFormattedDate()} | ⏲ ${state.selectedTime ?: ""}",
                    color = colorResource(R.color.gray_color),
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Divider(
                modifier = Modifier.padding(top = 4.dp, start = 29.dp, end = 29.dp),
                color = colorResource(R.color.gray_color)
            )
            TextField(
                value = state.description ?: "",
                onValueChange = {
                    coroutineScope.launch {

                    viewModel.onIntent(TodoIntent.DescriptionChanged(it))
                    }
                                },
                placeholder = {
                    Text(
                        "Description",
                        color = colorResource(R.color.white).copy(alpha = 0.5f),
                        fontSize = 18.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomImageButton(
                        text = "Delete",
                        onClick = {
                            showDeleteDialog = true
                        },
                        icon = R.drawable.del_ic,
                        modifier = Modifier.padding(end = 20.dp)
                    )

                    CustomImageButton(
                        text = "Update",
                        onClick = {
                            coroutineScope.launch {

                            viewModel.onIntent(TodoIntent.SaveTodo)
                            }
                                  },
                        icon = R.drawable.edit_ic,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
