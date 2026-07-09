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

abstract class BaseViewModel
<
        STATE : UiState,
        INTENT : UiIntent,
        EFFECT : UiEffect
        >
    (
    initialState: STATE
) : ViewModel(), MviViewModel<STATE, INTENT, EFFECT> {

    private val _uiState = MutableStateFlow(initialState)
    override val uiState: StateFlow<STATE> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<EFFECT>()
    override val effect: SharedFlow<EFFECT> = _effect.asSharedFlow()

    protected val currentState: STATE
        get() = _uiState.value

    protected fun updateState(reducer: (STATE) -> STATE) {
        _uiState.update(reducer)
    }

    protected fun sendEffect(effect: EFFECT) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    abstract override fun onIntent(intent: INTENT)
}