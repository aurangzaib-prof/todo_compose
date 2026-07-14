package com.example.todoapp.ui.presentation.home

import com.example.todoapp.ui.navigation.BottomNavItem

data class MainStates(
    val isLoading: Boolean = false,
    val selectedTab: BottomNavItem = BottomNavItem.Home,
)
