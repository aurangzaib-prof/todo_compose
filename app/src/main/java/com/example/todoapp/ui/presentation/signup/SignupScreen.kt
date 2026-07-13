package com.example.todoapp.ui.presentation.signup

import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.base.Home
import com.example.todoapp.base.Login
import com.example.todoapp.ui.presentation.components.CustomAuthButton
import com.example.todoapp.ui.presentation.components.CustomTextField
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
@Composable
fun SignupScreen(
    navController: NavHostController,
    viewModel: SignupViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->

            Log.d("effect_state", effect.toString())
                    navController.navigate(Login)
            state.isLoading = false
//            when (effect) {
//                SignupEffect.NavigateToLogin -> {
//                }
//                SignupEffect.NavigateToHome -> {
//                    navController.navigate(Home) {
//                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
//                    }
//                }
//            }
        }
    }

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
                "Welcome to DO IT ",
                fontSize = 30.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 20.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                "Create an account and Join us now!",
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
                value = state.name,
                onValueChange = {
                    coroutineScope.launch {

                    viewModel.onIntent(SignupIntent.NameChanged(it))
                    }
                                },
                hint = "Name",
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.account_icon),
                        contentDescription = "Email Icon",
                    )
                })
            Spacer(modifier = Modifier.padding(top = 17.dp))

            CustomTextField(
                value = state.email,
                onValueChange = {
                    coroutineScope.launch {

                    viewModel.onIntent(SignupIntent.EmailChanged(it))
                    }
                                },
                hint = "Email",
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.email_leading_ic),
                        contentDescription = "Email Icon",
                    )
                })

            Spacer(modifier = Modifier.padding(top = 17.dp))
            CustomTextField(
                value = state.password,
                onValueChange = {
                    coroutineScope.launch { viewModel.onIntent(SignupIntent.PasswordChanged(it)) }},
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
                    onClick = {
                        coroutineScope.launch {

                        viewModel.onIntent(SignupIntent.SignupClicked)
                        }
                              },
                    modifier = Modifier.fillMaxWidth(),
                    text = "Sign Up"
                )
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Already have an account?",
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Text(
                    "sign in",
                    fontSize = 20.sp,
                    color = colorResource(R.color.text_blue_color),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable {
                            coroutineScope.launch {

                            viewModel.onIntent(SignupIntent.SigninClicked)
                            }
                        },
                )
            }
        }
    }
}
