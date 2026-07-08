package com.example.todoapp.ui.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.ui.utils.Utils.formatDate


@Preview(showBackground = true)
@Composable
fun TodoCard(
    todo: TodoEntity
) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(5.dp),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor =
                Color.White, contentColor = Color.Black
        )
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
                    bottom = 5.dp
                )
            ) {
                Spacer(modifier = Modifier.padding(3.dp))
                Text(todo.title, fontSize = 16.sp)
                Text(todo.description, fontSize = 12.sp)
                Text(
                    "${formatDate(todo.date)} | ${todo.time}",
                    color = colorResource(R.color.dark_gray_color),
                    fontSize = 10.sp
                )
            }

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