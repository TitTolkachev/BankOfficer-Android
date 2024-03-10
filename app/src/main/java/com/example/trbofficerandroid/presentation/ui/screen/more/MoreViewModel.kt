package com.example.trbofficerandroid.presentation.ui.screen.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.usecase.UpdateUserIdUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class MoreViewModel(
    private val updateUserIdUseCase: UpdateUserIdUseCase
) : ViewModel() {

    private val _navigateToSignIn = MutableSharedFlow<Unit>()
    val navigateToSignIn: SharedFlow<Unit> = _navigateToSignIn
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    fun logout() = viewModelScope.launch {
        try {
            updateUserIdUseCase(null)
            _navigateToSignIn.emit(Unit)
        } catch (_: Exception) {
        }
    }
}