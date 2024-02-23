package com.example.hbofficerandroid.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbofficerandroid.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _activeScreen: MutableStateFlow<Screen> = MutableStateFlow(START_SCREEN)
    val activeScreen: StateFlow<Screen> = _activeScreen.asStateFlow()

    val fabVisible: StateFlow<Boolean> = _activeScreen.map {
        when (it) {
            Screen.Loans, Screen.Users, Screen.Rates -> true
            else -> false
        }
    }.stateIn(viewModelScope, WhileSubscribed(), false)

    private val _fabActions = MutableSharedFlow<Unit>()
    val fabActions = _fabActions.shareIn(viewModelScope, WhileSubscribed())

    private val _bottomNavActions = MutableSharedFlow<String>()
    val bottomNavActions = _bottomNavActions.shareIn(viewModelScope, WhileSubscribed())

    fun onBottomNavItemClick(item: Screen) {
        viewModelScope.launch {
            _bottomNavActions.emit(item.route)
            _activeScreen.update { item }
        }
    }

    fun onFabClick() {
        viewModelScope.launch {
            _fabActions.emit(Unit)
        }
    }

    companion object {
        val START_SCREEN = Screen.Main
        val SCREENS = listOf(
            Screen.Main,
            Screen.Loans,
            Screen.Users,
            Screen.Rates,
            Screen.More,
        )
    }
}