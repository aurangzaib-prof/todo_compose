package com.example.todoapp.ui.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.base.Login
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                OnboardingEffect.NavigateToLogin -> {
                    navController.navigate(Login) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                }
            }
        }
    }

    val data = when (state.currentPage) {
        0 -> OnboardingData(
            R.drawable.todo_notepad,
            "Plan your tasks to do, that\nway you’ll stay organized\nand you won’t skip any",
            400, 40, R.drawable.next_button
        )

        1 -> OnboardingData(
            R.drawable.onboardtwo,
            "Make a full schedule for\nthe whole week and stay\norganized and productive\nall days",
            300, 40, R.drawable.next_button
        )

        2 -> OnboardingData(
            R.drawable.onboardthree,
            "Create a team task, invite\npeople and manage your\nwork together",
            260, 118, R.drawable.next_button
        )

        else -> OnboardingData(
            R.drawable.onboardfour,
            "Your information is\nsecure with us",
            400, 20, R.drawable.done
        )
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
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(data.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(data.imageSize.dp)
                    .padding(top = 10.dp)
            )
            Spacer(modifier = Modifier.height(data.spacerTop.dp))
            Text(
                data.text,
                modifier = Modifier.padding(horizontal = 20.dp),
                fontSize = 25.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp, end = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.slider_threedot),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(data.buttonRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterEnd)
                        .clickable {
                            coroutineScope.launch {
                            viewModel.onIntent(OnboardingIntent.NextPage)

                            }

                        }
                )
            }
        }
    }
}

private data class OnboardingData(
    val imageRes: Int,
    val text: String,
    val imageSize: Int,
    val spacerTop: Int,
    val buttonRes: Int
)
