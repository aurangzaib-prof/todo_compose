package com.example.todoapp.ui.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R

@Composable
fun CustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier=modifier,
        shape = RoundedCornerShape(13.dp),
        placeholder = { Text("Search a task", color = colorResource(R.color.search_hint_color)) },
        singleLine = true,

        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.search_icon),
                contentDescription = "Search Icon",
                tint = colorResource(R.color.search_hint_color)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorResource(R.color.sort_btn_color),
            unfocusedContainerColor =colorResource(R.color.sort_btn_color),
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
//            cursorColor = colorResource(R.color.search_color)
        )
    )
}