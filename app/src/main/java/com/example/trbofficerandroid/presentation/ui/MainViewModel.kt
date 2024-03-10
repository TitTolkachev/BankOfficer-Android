package com.example.trbofficerandroid.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.usecase.GetUserIdUseCase
import com.example.trbofficerandroid.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {

    private val _startDestination = MutableStateFlow<Screen?>(null)
    val startDestination: StateFlow<Screen?> = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            val userId = try {
                getUserIdUseCase()
            } catch (_: Exception) {
                null
            }

            val startDestination = if (userId.isNullOrBlank()) Screen.SignIn else Screen.Home

            _startDestination.emit(startDestination)
        }
    }
}