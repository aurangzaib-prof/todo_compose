package com.example.todoapp.ui.presentation.home_screen

import androidx.lifecycle.viewModelScope
import com.example.todoapp.base.BaseViewModel
import com.example.todoapp.data.local.datastore.PreferenceManager
import com.example.todoapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authRepository: AuthRepository,
    private val preferenceManager: PreferenceManager
) : BaseViewModel<HomeState, HomeIntent, HomeEffect>(HomeState()) {

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            preferenceManager.getUserEmail().collectLatest { email ->
                if (email.isNotEmpty()) {
                    val user = authRepository.getUserByEmail(email)
                    user?.let {
                        updateState { state ->
                            state.copy(
                                username = it.name,
                                email = it.email
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onIntent(intent: HomeIntent) {
    }
}
