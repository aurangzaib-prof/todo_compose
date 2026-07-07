package com.example.todoapp.ui.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.todoapp.ui.navigation.BottomNavItem

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
@Composable
fun BottomBar(navController: NavHostController) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.TaskScreen,
        BottomNavItem.Calender,
        BottomNavItem.Settings,
    )

    NavigationBar(
        containerColor = Color.Transparent,
        contentColor = Color.White,
        tonalElevation = 0.dp
    ) {

        val currentDestination =
            navController.currentBackStackEntryAsState().value?.destination

        items.forEach { item ->

            NavigationBarItem(
                selected = currentDestination?.route == item.route,

                onClick = {
                    navController.navigate(item.route) {

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },

                label = {
                    Text(item.title)
                }
            )
        }
    }
}