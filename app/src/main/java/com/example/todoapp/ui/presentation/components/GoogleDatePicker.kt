package com.example.todoapp.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.R
import java.time.Instant
import java.time.ZoneId
@Preview(showBackground = true)
@Composable
fun GoogleDatePicker() {
    var showPicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color = Color.White)
    ) {

        Text(
            "Set Task for date", fontSize = 18.sp,
            modifier = Modifier.padding(10.dp, top = 20.dp)
        )

        Row(
            modifier =
                Modifier
                    .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { },
                shape = RoundedCornerShape(1.dp),
                placeholder =
                    {
                        Text(
                            "Task",
                            color = Color.White,
                            modifier = Modifier,
                        )
                    },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .height(height = 70.dp)
                    .weight(2f)
                    .padding(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = colorResource(R.color.sort_btn_color),
                    unfocusedContainerColor = colorResource(R.color.sort_btn_color),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),

                )
            Button(
                onClick = {},
                modifier = Modifier
                    .height(height = 50.dp)
                    .padding(end = 15.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.save_btn)
                ),
                shape = RoundedCornerShape(1.dp)
            ) {
                Text(
                    text = "Save", color = Color.White, fontSize = 16.sp
                )
            }
        }


        Text(text = selectedDate?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate().toString()
        } ?: "")
    }

    if (showPicker) {
        Dialog(onDismissRequest = { showPicker = false }) {

            Surface(
                shape = MaterialTheme.shapes.large
            ) {
                Column {
                    DatePicker(
                        state = datePickerState
                    )

                    Row {
                        TextButton(onClick = { showPicker = false }) {
                            Text("Cancel")
                        }

                        TextButton(onClick = {
                            selectedDate = datePickerState.selectedDateMillis
                            showPicker = false
                        }) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    }
}