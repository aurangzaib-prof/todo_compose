package com.example.todoapp.ui.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.base.Home
import com.example.todoapp.base.OnboardingOne
import com.example.todoapp.base.Splash
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            delay(2000)
            when (effect) {
                SplashEffect.NavigateToHome -> {
                    navController.navigate(Home) {
                        popUpTo(Splash) { inclusive = true }
                    }
                }
                SplashEffect.NavigateToOnboarding -> {
                    navController.navigate(OnboardingOne) {
                        popUpTo(Splash) { inclusive = true }
                    }
                }
            }
        }
    }
    
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
            ), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.checkmark), "check",
                modifier = Modifier.size(110.dp)
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text("Do it", fontSize = 44.sp, color = Color.White, fontFamily = FontFamily.Serif)
        }
    }
}
