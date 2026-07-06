package com.example.todoapp.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
@Composable
fun CustomSheetButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: Int
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(width = 170.dp, height = 45.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.sort_btn_color)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(25.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}