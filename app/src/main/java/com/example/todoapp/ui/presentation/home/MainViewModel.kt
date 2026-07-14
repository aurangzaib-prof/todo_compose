package com.example.todoapp.ui.presentation.home

import androidx.lifecycle.ViewModel
import com.example.todoapp.ui.navigation.BottomNavItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel(){
    private val _state = MutableStateFlow(MainStates())
    val state = _state.asStateFlow()

    fun onTabClicked(bottomNavItem: BottomNavItem){
        _state.value = _state.value.copy(
            selectedTab = bottomNavItem
        )
    }

}
