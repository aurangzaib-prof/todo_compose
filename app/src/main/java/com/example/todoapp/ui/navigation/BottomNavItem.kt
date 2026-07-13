package com.example.todoapp.ui.navigation

import com.example.todoapp.R

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomNavItem("home", "Home", R.drawable.home_bnave)
    object TaskDetail : BottomNavItem("task_list", "Task Detail", R.drawable.todo_bnav)
    object Calender : BottomNavItem("calender", "Calender", R.drawable.calender_bnav)
    object TaskScreen : BottomNavItem("task_screen", "Task", R.drawable.todo_bnav)
    object Settings : BottomNavItem("settings", "Settings", R.drawable.settings_bnav)
}
