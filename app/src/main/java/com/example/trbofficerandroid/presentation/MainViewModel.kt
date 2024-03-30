package com.example.trbofficerandroid.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.data.local.PrefsDataStore
import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.data.remote.repository.PrefsRepositoryImpl
import com.example.trbofficerandroid.domain.model.AppTheme
import com.example.trbofficerandroid.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val authService: AuthService,
    private val prefsRepositoryImpl: PrefsRepositoryImpl,
    private val prefsDataStore: PrefsDataStore
) : ViewModel() {

    private val _startDestination = MutableStateFlow<Screen?>(null)
    val startDestination: StateFlow<Screen?> = _startDestination.asStateFlow()

    val appTheme = prefsDataStore.appThemeFlow

    init {
        viewModelScope.launch {
            val startDestination = if (authService.getSignedInUser() == null) {
                Screen.SignIn
            } else Screen.Home

            _startDestination.emit(startDestination)
        }

        viewModelScope.launch {
            val token = authService.getSignedInUser()?.token
            val theme = token?.let {
                prefsRepositoryImpl.getAppTheme(token)
            } ?: AppTheme.UNSPECIFIED

            prefsDataStore.updateAppTheme(theme)
        }
    }
}