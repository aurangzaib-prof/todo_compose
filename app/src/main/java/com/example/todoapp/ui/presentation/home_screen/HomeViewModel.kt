package com.example.todoapp.ui.presentation.home_screen

import androidx.lifecycle.viewModelScope
import com.example.todoapp.base.BaseViewModel
import com.example.todoapp.data.local.datastore.PreferenceManager
import com.example.todoapp.data.repository.MainRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authRepository: MainRepository,
    private val preferenceManager: PreferenceManager,
) : BaseViewModel<HomeState, HomeIntent, HomeEffect>(HomeState()) {

    init {
        loadUserData()
        getAllTodos()
    }

    private fun getAllTodos() {
        viewModelScope.launch {
            authRepository.getAllTodos().collect { todos ->
                val completed = todos.filter { it.isCompleted }
                    .sortedByDescending { it.id }
                    .take(3)

                val incompleted = todos.filter { !it.isCompleted }
                    .take(3)

                updateState { state ->
                    state.copy(
                        completedTodos = completed,
                        incompletedTodos = incompleted
                    )
                }
            }
        }
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

    override suspend fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.TodoClicked -> {
                sendEffect(HomeEffect.NavigateToDetails(intent.todo))
            }
        }
    }
}
