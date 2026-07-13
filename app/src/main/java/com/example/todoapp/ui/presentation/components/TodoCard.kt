package com.example.todoapp.ui.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import com.example.todoapp.ui.presentation.calender_screen.CalenderViewModel
import com.example.todoapp.ui.presentation.todo.TodoIntent
import com.example.todoapp.ui.extensions.toFormattedDate
import kotlinx.coroutines.launch

@Composable
fun TodoCard(
    todo: TodoEntity,
    viewModel: CalenderViewModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor =
                Color.White, contentColor = Color.Black
        ),
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 5.dp,
                    bottom = 0.dp
                )
            ) {
                Spacer(modifier = Modifier.padding(3.dp))
                Text(todo.title, fontSize = 16.sp)
                todo.description?.let { todoDescription ->
                    Text(todoDescription, fontSize = 12.sp)
                }
                Text(
                    "${todo.date.toFormattedDate()} | ${todo.time}",
                    color = colorResource(R.color.dark_gray_color),
                    fontSize = 10.sp
                )
            }

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    colors = CheckboxDefaults.colors(
                        checkedColor =
                            colorResource(R.color.blue_color), uncheckedColor = Color.DarkGray,
                        checkmarkColor = Color.White
                    ),
                    checked = todo.isCompleted,
                    onCheckedChange = { checked ->
                        coroutineScope.launch {

                            viewModel.onIntent(
                                TodoIntent.UpdateTodo(
                                    todo.copy(isCompleted = checked)
                                )
                            )
                        }
                    }
                )
                IconButton(
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.forward_arrow),
                        "forward_arrow",
                        tint = colorResource(R.color.blue_color)
                    )
                }
            }
        }
    }
}
