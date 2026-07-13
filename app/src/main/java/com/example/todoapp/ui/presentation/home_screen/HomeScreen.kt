package com.example.todoapp.ui.presentation.home_screen

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.base.TaskDetail
import com.example.todoapp.ui.presentation.components.TodoCard
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToDetails -> {
                    val todoJson = Uri.encode(Json.encodeToString(effect.todo))
                    navController.navigate(TaskDetail(todoJson))
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.home_icon),
                contentDescription = "User Logo",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.offset(y = 6.dp)) {
                Text(
                    text = state.username.ifEmpty { "User" },
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.email.ifEmpty { "email@example.com" },
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {

            if (state.completedTodos.isNotEmpty()) {
                item {
                    Text(
                        text = "Completed Task!",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                items(
                    items = state.completedTodos,
                    key = { todo -> todo.id }
                ) { todo ->

                    TodoCard(
                        todo,
                        koinViewModel(),
                        Modifier.animateItem(
                            fadeOutSpec = null
                        ),
                        onClick = {
                            coroutineScope.launch {

                                viewModel.onIntent(
                                    HomeIntent.TodoClicked(todo)
                                )
                            }
                        }
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(20.dp))


        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (state.incompletedTodos.isNotEmpty()) {
                item {
                    AnimatedVisibility(
                        visible = state.incompletedTodos.isNotEmpty(),
                        enter = fadeIn() + scaleIn(),
                        exit = fadeOut() + scaleOut(),
                    ) {
                        Text(
                            text = "Incomplete Task!",
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                }
            }
            items(
                items = state.incompletedTodos,
                key = { todo -> todo.id }
            ) { todo ->
                TodoCard(
                    todo, koinViewModel(), Modifier.animateItem(
                        fadeOutSpec = null
                    ),
                    onClick = {
                        coroutineScope.launch {

                            viewModel.onIntent(
                                HomeIntent.TodoClicked(todo)
                            )
                        }                    }
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {

            Image(
                painter = painterResource(id = R.drawable.home_icon),
                contentDescription = "User Logo",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.offset(y = 6.dp)) {
                Text(
                    text = "Preview User",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "preview@example.com",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Incomplete Task!",
            color = Color.White,
            fontSize = 18.sp
        )
    }
}
