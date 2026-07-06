package com.example.todoapp.ui.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.navigation.AppNavGraph
import com.example.todoapp.ui.presentation.components.BottomBar

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.R
@RequiresApi
    (Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
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
        containerColor = Color.Transparent,
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->

        Box(modifier = Modifier.padding(bottom = padding.calculateBottomPadding())) {
            AppNavGraph(navController)
        }
    }
}