package com.example.todoapp.ui.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.ui.presentation.components.CustomImageButton
@Preview(showBackground = true)
@Composable
fun TaskDetailScreen() {
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

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(R.drawable.back_ic),
                    "back_ic",
                    modifier = Modifier.size(20.dp)
                )
                Text("Task Details", color = Color.White)
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
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .width(150.dp)
                        .padding(top = 20.dp),
                    placeholder = {
                        Text("team meeting", color = Color.White)
                    }, maxLines = 1,
                    colors = TextFieldDefaults.colors(
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
                    modifier = Modifier.padding(top = 20.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 13.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(13.dp),

                ) {
                Spacer(modifier = Modifier.padding(start = 10.dp, top = 0.dp))
                Image(painter = painterResource(R.drawable.calender_ic), "calender")

                Text("Today | ⏲ 20:00", color = colorResource(R.color.gray_color), fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Divider(
                modifier = Modifier.padding(top = 4.dp, start = 29.dp, end = 29.dp),
                color = colorResource(R.color.gray_color)
            )
            TextField(
                value = "",
                onValueChange = {}, placeholder = {
                    Text(
                        "Description",
                        color = colorResource(R.color.white),
                        fontSize = 18.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp),

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomImageButton(
                    text = "Done", onClick = {},
                    icon = R.drawable.done_ic,
                    modifier = Modifier.padding(top = 350.dp, end = 20.dp)
                )

                CustomImageButton(
                    text = "Delete", onClick = {},
                    icon = R.drawable.del_ic,
                    modifier = Modifier.padding(top = 350.dp)
                )
            }

        }
    }

}