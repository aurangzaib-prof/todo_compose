package com.example.todoapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<state : UiState, intent : UiIntent, effect : UiEffect>(
    initialState: state
) : ViewModel(), MviViewModel<state, intent, effect> {
    private val _uiState = MutableStateFlow(initialState)
    override val uiState: StateFlow<state> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<effect>()
    override val effect: SharedFlow<effect> = _effect.asSharedFlow()

    protected val currentState: state
        get() = _uiState.value

    abstract override fun onIntent(intent: intent)

    protected fun updateState(reducer: (state) -> state) {
        _uiState.update(reducer)
    }

    protected fun sendEffect(effect: effect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}
