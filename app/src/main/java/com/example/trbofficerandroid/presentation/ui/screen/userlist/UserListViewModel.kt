package com.example.trbofficerandroid.presentation.ui.screen.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.usecase.GetClientListUseCase
import com.example.trbofficerandroid.domain.usecase.GetOfficerListUseCase
import com.example.trbofficerandroid.presentation.ui.screen.userlist.mapper.UserMapper.toUi
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState.CLIENT
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserShort
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getClientListUseCase: GetClientListUseCase,
    private val getOfficerListUseCase: GetOfficerListUseCase,
) : ViewModel() {

    private val _clients: MutableStateFlow<List<UserShort>?> = MutableStateFlow(null)
    val clients = _clients.asStateFlow()

    private val _officers: MutableStateFlow<List<UserShort>?> = MutableStateFlow(null)
    val officers = _officers.asStateFlow()

    private val _searchActive = MutableStateFlow(false)
    val searchActive: StateFlow<Boolean> = _searchActive.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _activeTab = MutableStateFlow(CLIENT)
    val activeTab: StateFlow<UserListTabState> = _activeTab.asStateFlow()

    init {
        loadUsers()
    }

    fun onTabStateChange(state: UserListTabState) {
        _activeTab.update { state }
    }

    fun onSearchBarStateChange(active: Boolean) {
        _searchActive.update { active }
    }

    fun onSearchQueryChange(value: String) {
        _searchQuery.update { value }
    }

    private fun loadUsers() = viewModelScope.launch {
        _clients.update { getClientListUseCase().toUi() }
        _officers.update { getOfficerListUseCase().toUi() }
    }
}