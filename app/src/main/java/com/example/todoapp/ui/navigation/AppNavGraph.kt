package com.example.todoapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapp.ui.presentation.home.CalenderScreen
import com.example.todoapp.ui.presentation.home.HomeScreen
import com.example.todoapp.ui.presentation.home.TaskDetailScreen
import com.example.todoapp.ui.presentation.home.TasksScreen
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) { HomeScreen() }
        composable(BottomNavItem.Calender.route) { CalenderScreen(navController) }
        composable(BottomNavItem.TaskDetail.route) {
        }
        composable(BottomNavItem.TaskScreen.route) { TasksScreen(navController) }
    }
}