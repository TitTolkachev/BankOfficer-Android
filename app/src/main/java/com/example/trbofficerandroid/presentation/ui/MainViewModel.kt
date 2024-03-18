package com.example.trbofficerandroid.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val authService: AuthService
) : ViewModel() {

    private val _startDestination = MutableStateFlow<Screen?>(null)
    val startDestination: StateFlow<Screen?> = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {

            val startDestination = if (authService.getSignedInUser() == null) {
                Screen.SignIn
            } else Screen.Home

            _startDestination.emit(startDestination)
        }
    }
}