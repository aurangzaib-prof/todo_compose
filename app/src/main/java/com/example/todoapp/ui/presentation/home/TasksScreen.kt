package com.example.todoapp.ui.presentation.home

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.R
import com.example.todoapp.ui.presentation.components.CustomSearchBar
import com.example.todoapp.ui.presentation.components.CustomSheetButton
import com.example.todoapp.ui.presentation.components.SheetTextField
import com.example.todoapp.ui.presentation.components.TaskTitleTextField
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun showUi() {
    val navController = rememberNavController()
    TasksScreen(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(navController: NavHostController) {
    var showBottomSheet by rememberSaveable { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState()
    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    @OptIn(ExperimentalMaterial3Api::class)
    var showTimePicker by remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.bg_top_color),
                        colorResource(R.color.bg_bottom_color)
                    )
                )
            ), floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet = true
                },
                containerColor = colorResource(id = R.color.fab_color),
                shape = RoundedCornerShape(116.dp),
            ) {
                Image(painter = painterResource(R.drawable.plus_icon), "plus")
            }
        }, containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {


            val timePickerState = rememberTimePickerState(
                initialHour = 12,
                initialMinute = 0,
                is24Hour = false
            )
            if (showTimePicker) {


                AlertDialog(
                    onDismissRequest = {
                        showTimePicker = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                val hour = timePickerState.hour
                                val minute = timePickerState.minute


                                val hour12 = when {
                                    hour == 0 -> 12
                                    hour > 12 -> hour - 12
                                    else -> hour
                                }

                                val amPm = if (hour < 12) "AM" else "PM"

                                Toast.makeText(
                                    context,
                                    String.format(
                                        "Selected time: %d:%02d %s",
                                        hour12,
                                        minute,
                                        amPm
                                    ),
                                    Toast.LENGTH_SHORT
                                ).show()

                                showTimePicker = false
                            }
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showTimePicker = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    },
                    text = {
                        TimePicker(
                            state = timePickerState
                        )
                    }
                )


            }

            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = {
                        showDatePicker = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                val selectedDate = datePickerState.selectedDateMillis

                                selectedDate?.let {
                                    val formatter = SimpleDateFormat(
                                        "dd/MM/yyyy",
                                        Locale.getDefault()
                                    )

                                    val formattedDate = formatter.format(Date(it))


                                    Toast.makeText(
                                        context,
                                        "Selected date: $formattedDate",
                                        Toast.LENGTH_SHORT
                                    ).show()
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
                    DatePicker(
                        state = datePickerState
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomSearchBar(
                        modifier = Modifier.weight(1f), query = "", onQueryChange = {})

                    Button(
                        onClick = {},
                        modifier = Modifier.height(56.dp),
                        shape = RoundedCornerShape(13.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.sort_btn_color),
                            contentColor = Color.White
                        ),
                    ) {
                        Image(
                            painter = painterResource(R.drawable.sort_btn_icon),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Sort", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(10.dp))

                        Image(
                            painter = painterResource(R.drawable.see_more_icon),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    "Tasks list", color = colorResource(R.color.white), fontSize = 22.sp
                )
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    }, sheetState = sheetState,
                    containerColor = colorResource(id = R.color.white)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                        ) {
                            TaskTitleTextField(
                                value = "",
                                onValueChange = {},
                                modifier = Modifier,
                                hint = "Task title..."
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            SheetTextField(
                                value = "",
                                onValueChange = {

                                },
                                modifier = Modifier.padding(0.dp),
                                hint = "Task description...",
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                CustomSheetButton(
                                    text = "Date", onClick = {
                                        showDatePicker = true
                                    }, icon = R.drawable.calender_ic
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                CustomSheetButton(
                                    text = "Time", onClick = {
                                        showTimePicker = true
                                    }, icon = R.drawable.clock_ic
                                )

                            }

                            Spacer(modifier = Modifier.height(30.dp))


                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    onClick = { },
                                    border = BorderStroke(
                                        2.dp, colorResource(R.color.btn_border_color)
                                    ),
                                    modifier = Modifier.size(width = 170.dp, height = 45.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.White,

                                        ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {

                                    Text(
                                        text = "Cancel", color = Color.Black, fontSize = 16.sp
                                    )
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                Button(
                                    onClick = { },
                                    modifier = Modifier.size(width = 170.dp, height = 45.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorResource(R.color.btn_border_color),

                                        ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {

                                    Text(
                                        text = "Create", color = Color.White, fontSize = 16.sp
                                    )
                                }
                            }


                        }

                    }
                }
            }
        }

    }

}
