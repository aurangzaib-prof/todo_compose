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
<S : UiState, I : UiIntent, E : UiEffect>
    (
    initialState: S
) : ViewModel(), MviViewModel<S, I, E> {

    private val _uiState = MutableStateFlow(initialState)
    override val uiState: StateFlow<S> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<E>()
    override val effect: SharedFlow<E> = _effect.asSharedFlow()

    protected val currentState: S
        get() = _uiState.value

    protected fun updateState(reducer: (S) -> S) {
        _uiState.update(reducer)
    }

    protected fun sendEffect(effect: E) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    abstract override suspend fun onIntent(intent: I)
}