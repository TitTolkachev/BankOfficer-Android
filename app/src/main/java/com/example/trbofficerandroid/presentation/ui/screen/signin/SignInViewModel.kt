package com.example.trbofficerandroid.presentation.ui.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    private val _navigateToHome = MutableSharedFlow<Unit>()
    val navigateToHome: SharedFlow<Unit> = _navigateToHome
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun onEmailChange(newValue: String) {
        _email.update { newValue }
    }

    fun onPasswordChange(newValue: String) {
        _password.update { newValue }
    }

    fun signIn() = viewModelScope.launch {
        if (_loading.value)
            return@launch

        val email = _email.value
        val password = _password.value

        _loading.update { true }
        try {
            signInUseCase(email = email, password = password)
            _navigateToHome.emit(Unit)
        } catch (_: Exception) {
            _error.emit("Не удалось войти")
        }
        _loading.update { false }
    }
}