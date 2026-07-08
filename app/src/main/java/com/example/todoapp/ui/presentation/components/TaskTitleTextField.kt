package com.example.todoapp.ui.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
@Composable
fun TaskTitleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    isCompleted: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(8.dp),
        placeholder = { Text(hint, color = Color.White) },
        singleLine = true,
        leadingIcon = {
            Checkbox(
                isCompleted,
                onCheckedChange =  onCheckedChange ,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    uncheckedColor = Color.White
                )
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorResource(R.color.sort_btn_color),
            unfocusedContainerColor = colorResource(R.color.sort_btn_color),
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black
        )
    )
}
