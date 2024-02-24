package com.example.trbofficerandroid.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.presentation.navigation.HomeScreen
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

class HomeViewModel : ViewModel() {

    private val _activeScreen: MutableStateFlow<HomeScreen> = MutableStateFlow(START_SCREEN)
    val activeScreen: StateFlow<HomeScreen> = _activeScreen.asStateFlow()

    val fabVisible: StateFlow<Boolean> = _activeScreen.map {
        when (it) {
            HomeScreen.UserList, HomeScreen.RateList -> true
            else -> false
        }
    }.stateIn(viewModelScope, WhileSubscribed(), false)

    private val _fabActions = MutableSharedFlow<Unit>()
    val fabActions = _fabActions.shareIn(viewModelScope, WhileSubscribed())

    private val _bottomNavActions = MutableSharedFlow<String>()
    val bottomNavActions = _bottomNavActions.shareIn(viewModelScope, WhileSubscribed())

    fun onBottomNavItemClick(item: HomeScreen) {
        viewModelScope.launch {
            _bottomNavActions.emit(item.route)
        }
    }

    fun onActiveScreenChange(route: String?) {
        val screen = SCREENS.find { it.route == route } ?: return
        _activeScreen.update { screen }
    }

    fun onFabClick() {
        viewModelScope.launch {
            _fabActions.emit(Unit)
        }
    }

    companion object {
        val START_SCREEN = HomeScreen.Main
        val SCREENS = listOf(
            HomeScreen.Main,
            HomeScreen.LoanList,
            HomeScreen.UserList,
            HomeScreen.RateList,
            HomeScreen.More,
        )
    }
}