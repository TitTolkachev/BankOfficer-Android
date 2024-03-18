package com.example.trbofficerandroid.presentation.ui.screen.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.data.remote.AuthService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoreViewModel(
    private val authService: AuthService
) : ViewModel() {

    private val _userPhoto = MutableStateFlow<String?>(null)
    val userPhoto = _userPhoto.asStateFlow()

    private val _navigateToSignIn = MutableSharedFlow<Unit>()
    val navigateToSignIn: SharedFlow<Unit> = _navigateToSignIn
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    init {
        loadUserPhoto()
    }

    fun logout() = viewModelScope.launch {
        try {
            authService.signOut()
            _navigateToSignIn.emit(Unit)
        } catch (_: Exception) {
        }
    }

    private fun loadUserPhoto() = viewModelScope.launch {
        val user = authService.getSignedInUser()
        _userPhoto.update { user?.profilePictureUrl }
    }
}