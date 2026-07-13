package com.example.todoapp.ui.presentation.calender_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.base.BaseScreen
import com.example.todoapp.ui.presentation.components.AddTaskDialogue
import com.example.todoapp.ui.presentation.todo.TodoEffect
import com.example.todoapp.ui.presentation.todo.TodoIntent
import com.example.todoapp.ui.extensions.toast
import com.kizitonwose.calendar.compose.rememberCalendarState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun showUiCal() {
    val navController = NavHostController(LocalContext.current)
    CalenderScreen(navController, koinViewModel())
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalenderScreen(
    navController: NavHostController,
    viewModel: CalenderViewModel = koinViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val currentMonth = YearMonth.now()
    val calendarState = rememberCalendarState(
        startMonth = currentMonth.minusMonths(12),
        endMonth = currentMonth.plusMonths(12),
        firstVisibleMonth = currentMonth
    )

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TodoEffect.ShowToast -> {
                    context.toast(effect.message)
                }
            }
        }
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState()

    BaseScreen(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Manage your time",
                        color = Color.White,
                        fontSize = 21.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.size(40.dp)) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.back_ic),
                                contentDescription = "back",
                                tint = colorResource(R.color.back_btn_bg),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = {
                        showDatePicker = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                datePickerState.selectedDateMillis?.let {
                                    coroutineScope.launch {
                                        viewModel.onIntent(TodoIntent.DateSelected(it))
                                    }
                                }
                                showDatePicker = false
                            }
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showDatePicker = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier.padding(10.dp)) {
                AddTaskDialogue(
                    onValueChange = {
                        coroutineScope.launch {
                            viewModel.onIntent(TodoIntent.TitleChanged(it))
                        }
                    },
                    value = state.title,
                    saveClicked = {
                        coroutineScope.launch {
                            viewModel.onIntent(TodoIntent.SaveTodo)
                        }
                    },
                    onCheckedChange = {
                        coroutineScope.launch {
                            viewModel.onIntent(TodoIntent.CompletedChanged(it))
                        }
                    },
                    isCompleted = state.isCompleted
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        showDatePicker = true
                    },
                    modifier = Modifier
                        .height(height = 50.dp)
                        .padding(end = 15.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.save_btn)
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        text = "Pick a date", color = Color.White, fontSize = 16.sp
                    )
                }
            }
        }
    }
}
