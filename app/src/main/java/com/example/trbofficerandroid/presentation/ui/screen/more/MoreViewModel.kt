package com.example.trbofficerandroid.presentation.ui.screen.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.data.local.PrefsDataStore
import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.data.remote.repository.PrefsRepositoryImpl
import com.example.trbofficerandroid.domain.model.AppTheme
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MoreViewModel(
    private val authService: AuthService,
    private val prefsRepositoryImpl: PrefsRepositoryImpl,
    private val prefsDataStore: PrefsDataStore,
) : ViewModel() {
    private val _userPhoto = MutableStateFlow<String?>(null)
    val userPhoto = _userPhoto.asStateFlow()

    val theme = prefsDataStore.appThemeFlow

    private val _link = MutableSharedFlow<String>()
    val link = _link.shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    init {
        loadUserPhoto()
    }

    fun logout() = viewModelScope.launch {
        try {
            authService.signOut()
            val token = FirebaseMessaging.getInstance().token.await()
            _link.emit("http://81.19.137.198:8086/Home/Logout?deviceId=$token")
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