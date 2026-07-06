package com.example.todoapp.ui.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.local.datastore.PreferenceManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

sealed class SplashEffect {
    object NavigateToHome : SplashEffect()
    object NavigateToOnboarding : SplashEffect()
}

class SplashViewModel(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _effect = MutableSharedFlow<SplashEffect>(replay = 1)
    val effect: SharedFlow<SplashEffect> = _effect.asSharedFlow()

    init {
        checkAuth()
    }

    private fun checkAuth() {
        viewModelScope.launch {
            val isLoggedIn = preferenceManager.isLoggedIn().first()
            if (isLoggedIn) {
                _effect.emit(SplashEffect.NavigateToHome)
            } else {
                _effect.emit(SplashEffect.NavigateToOnboarding)
            }
        }
    }
}
