package com.example.todoapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.base.Home
import com.example.todoapp.base.Login
import com.example.todoapp.base.OnboardingOne
import com.example.todoapp.base.Signup
import com.example.todoapp.base.Splash
import com.example.todoapp.ui.presentation.home.MainScreen
import com.example.todoapp.ui.presentation.login.LoginScreen
import com.example.todoapp.ui.presentation.onboarding.OnboardingScreen
import com.example.todoapp.ui.presentation.signup.SignupScreen
import com.example.todoapp.ui.presentation.splash.SplashScreen
import com.example.todoapp.ui.theme.TodoAppTheme

import androidx.activity.SystemBarStyle
import android.graphics.Color as AndroidColor

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                AndroidColor.TRANSPARENT,
                AndroidColor.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                AndroidColor.TRANSPARENT,
                AndroidColor.TRANSPARENT
            )
        )

        setContent {
            TodoAppTheme {

                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Splash,
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable<Splash> {
                            SplashScreen(navController)
                        }

                        composable<OnboardingOne> {
                            OnboardingScreen(navController)
                        }

                        composable<Login> {
                            LoginScreen(navController)
                        }

                        composable<Signup> {
                            SignupScreen(navController)
                        }
                        composable<Home> {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}