package com.example.trbofficerandroid.presentation.ui.screen.signin

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.data.remote.AuthService
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
    savedStateHandle: SavedStateHandle,
    private val authService: AuthService
) : ViewModel() {
    private val _navigateToHome = MutableSharedFlow<Unit>()
    val navigateToHome: SharedFlow<Unit> = _navigateToHome
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _success = MutableSharedFlow<String>()
    val success = _success.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _link = MutableSharedFlow<String>()
    val link = _link.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    // Приходит как аргумент при навигации из Chrome Custom Tabs
    private val _token: String? = savedStateHandle["token"]

    init {
        if (_token != null) {
            viewModelScope.launch {
                _loading.update { true }
                try {
                    authService.signInWithToken(_token)
                    _navigateToHome.emit(Unit)
                } catch (_: Exception) {
                    _error.emit("Не удалось войти")
                }
                _loading.update { false }
            }
        }
    }

    fun onResetPasswordClick() = viewModelScope.launch {
//        try {
//            authService.resetPassword(email = _email.value)
//            _success.emit("Письмо успешно отправлено на почту")
//        } catch (_: Exception) {
//            _error.emit("Не удалось сбросить пароль")
//        }
    }

    fun onSignInClick() = viewModelScope.launch {
        _link.emit("http://77.106.105.103:8089/Home/Index")
    }

    /**
     * Starts Launcher for an Activity result.
     */
    fun onSignInWithGoogleClick(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) =
        viewModelScope.launch {
            val signInIntentSender = authService.getGoogleSignInIntentSender()
            if (signInIntentSender == null) {
                _error.emit("Не удалось получить доступ к аккаунтам")
                return@launch
            }
            launcher.launch(IntentSenderRequest.Builder(signInIntentSender).build())
        }

    /**
     * Log in with Google Credentials.
     */
    fun signInWithGoogle(data: Intent?) = viewModelScope.launch {
        if (_loading.value || data == null)
            return@launch

        _loading.update { true }
        try {
            authService.signInWithGoogle(data)
            _navigateToHome.emit(Unit)
        } catch (_: Exception) {
            _error.emit("Не удалось войти с помощью Google")
        }
        _loading.update { false }
    }
}