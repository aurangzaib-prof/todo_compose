package com.example.todoapp.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R

@Preview(showBackground = true)
@Composable
fun OnboardingScreenFour() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.bg_top_color),
                        colorResource(id = R.color.bg_bottom_color)
                    )
                )
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 125.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.onboardfour),
                "desc",
                modifier = Modifier
                    .size(400.dp)
                    .padding(top = 10.dp)
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                "You informations are\nsecure with us",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                fontSize = 25.sp,
                color = Color.White,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
                textAlign = TextAlign.Center

            )
            Spacer(modifier = Modifier.padding(top = 130.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(R.drawable.slider_threedot), "threedot", modifier = Modifier.padding(start = 120.dp))

                Image(
                    painter = painterResource(R.drawable.done),
                    "next_button",
                    modifier = Modifier.size(100.dp)
                        .padding(start = 10.dp) )
            }

        }
    }
}

