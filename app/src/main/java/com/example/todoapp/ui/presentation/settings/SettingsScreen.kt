package com.example.todoapp.ui.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.base.BaseScreen
import com.example.todoapp.ui.presentation.components.SettingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    BaseScreen(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Settings",
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
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            SettingItem(
                iconRes = R.drawable.profile_icon,
                title = "Profile",
                onClick = {  }
            )
            SettingItem(
                iconRes = R.drawable.bell_icon,
                title = "Notifications",
                onClick = {  }
            )
            SettingItem(
                iconRes = R.drawable.terms_icon,
                title = "Terms & Conditions",
                onClick = {  }
            )
            SettingItem(
                iconRes = R.drawable.logout_icon,
                title = "Logout",
                onClick = {  },
                isLast = true
            )
        }
    }
}
