package com.example.trbofficerandroid.presentation.ui.screen.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.data.local.PrefsDataStore
import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.data.remote.repository.PrefsRepositoryImpl
import com.example.trbofficerandroid.domain.model.AppTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoreViewModel(
    private val authService: AuthService,
    private val prefsRepositoryImpl: PrefsRepositoryImpl,
    private val prefsDataStore: PrefsDataStore,
) : ViewModel() {

    private val _userPhoto = MutableStateFlow<String?>(null)
    val userPhoto = _userPhoto.asStateFlow()

    private val _navigateToSignIn = MutableSharedFlow<Unit>()
    val navigateToSignIn: SharedFlow<Unit> = _navigateToSignIn
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    val theme = prefsDataStore.appThemeFlow

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

    fun updateAppTheme(newTheme: AppTheme) = viewModelScope.launch {
        try {
            val token = authService.getSignedInUser()?.token ?: return@launch
            prefsRepositoryImpl.changeAppTheme(token = token, newTheme = newTheme)

            prefsDataStore.updateAppTheme(newTheme)
        } catch (_: Exception) {
        }
    }

    private fun loadUserPhoto() = viewModelScope.launch {
        val user = authService.getSignedInUser()
        _userPhoto.update { user?.profilePictureUrl }
    }
}