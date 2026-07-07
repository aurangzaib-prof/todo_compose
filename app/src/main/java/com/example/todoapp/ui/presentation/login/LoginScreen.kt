package com.example.todoapp.ui.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.R
import com.example.todoapp.base.Home
import com.example.todoapp.base.Login
import com.example.todoapp.base.Signup
import com.example.todoapp.ui.presentation.components.CustomAuthButton
import com.example.todoapp.ui.presentation.components.CustomTextField
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@Composable
@Preview(showBackground = true)
fun showUi() {
    LoginContent(
        state = LoginState(),
        onIntent = {}
    )
}

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                LoginEffect.NavigateToHome -> {
                    navController.navigate(Home) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }

                LoginEffect.NavigateToSignup -> {
                    navController.navigate(Signup)
                }
            }
        }
    }

    LoginContent(
        state = state,
        onIntent = { viewModel.onIntent(it) }
    )
}

@Composable
fun LoginContent(
    state: LoginState,
    onIntent: (LoginIntent) -> Unit
) {
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Image(
                painter = painterResource(R.drawable.checkmark),
                contentDescription = "check",
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.padding(top = 40.dp))
            Text(
                "Welcome Back to DO IT ",
                fontSize = 30.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 20.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                "Have an other productive day!",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            if (state.error != null) {
                Text(
                    text = state.error!!,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            CustomTextField(
                value = state.email,
                onValueChange = { onIntent(LoginIntent.EmailChanged(it)) },
                hint = "Email",
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.email_leading_ic),
                        contentDescription = "Email Icon",
                    )
                },

            )

            Spacer(modifier = Modifier.padding(top = 20.dp))
            CustomTextField(
                value = state.password,
                onValueChange = { onIntent(LoginIntent.PasswordChanged(it)) },
                hint = "Password",
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.pass_leading_ic),
                        contentDescription = "Pass Icon",
                    )
                })

            Spacer(modifier = Modifier.padding(top = 20.dp))

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.White
                )
            } else {
                CustomAuthButton(
                    onClick = { onIntent(LoginIntent.LoginClicked) },
                    modifier = Modifier.fillMaxWidth(),
                    text = "Sign In"
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Don’t have an account?",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Text(
                    "sign up!",
                    fontSize = 20.sp,
                    color = colorResource(R.color.text_blue_color),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable {
                            onIntent(LoginIntent.SignupClicked)
                        },
                )
            }
        }
    }
}
