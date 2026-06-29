package com.example.todoapp.ui.presentation

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R


@Preview(showBackground = true)
@Composable
fun SplashScreen() {
    Box (modifier = Modifier
        .fillMaxSize()
        .background(brush = Brush.verticalGradient(colors = listOf(
            colorResource(id = R.color.bg_top_color),
            colorResource(id = R.color.bg_bottom_color)
        ))), contentAlignment = Alignment.Center
     ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,) {
            Image(painter = painterResource(R.drawable.checkmark) ,"check",
                modifier = Modifier.size(110.dp))

            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text("Do it", fontSize = 44.sp, color = Color.White, fontFamily = FontFamily.Serif)
        }

    }


}