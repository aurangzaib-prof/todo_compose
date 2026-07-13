package com.example.todoapp.ui.navigation

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.todoapp.base.TaskDetail
import com.example.todoapp.ui.presentation.calender_screen.CalenderScreen
import com.example.todoapp.ui.presentation.home.TaskDetailScreen
import com.example.todoapp.ui.presentation.home_screen.HomeScreen
import com.example.todoapp.ui.presentation.task_screen.TasksScreen
import com.example.todoapp.data.local.room.todo_database.TodoEntity
import com.example.todoapp.ui.presentation.settings.SettingsScreen
import kotlinx.serialization.json.Json

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(navController)
        }
        composable(BottomNavItem.Calender.route) { it ->
            CalenderScreen(navController)
        }
        composable(BottomNavItem.TaskScreen.route) { TasksScreen(navController) }
        composable(BottomNavItem.Settings.route) {
            SettingsScreen(navController)
        }

        composable<TaskDetail> { backStackEntry ->
            val detail: TaskDetail = backStackEntry.toRoute<TaskDetail>()
            val todo = Json.decodeFromString<TodoEntity>(Uri.decode(detail.todoJson))
            TaskDetailScreen(navController, todo)
        }
    }
}
