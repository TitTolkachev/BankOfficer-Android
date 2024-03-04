package com.example.trbofficerandroid.presentation.ui.screen.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.ApiServiceGrpc
import com.example.trbofficerandroid.GetClientListRequest
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState.CLIENT
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserShort
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val api: ApiServiceGrpc.ApiServiceBlockingStub
) : ViewModel() {

    private val _clients: MutableStateFlow<List<UserShort>?> = MutableStateFlow(null)
    val clients = _clients.asStateFlow()

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
        val request = GetClientListRequest.newBuilder().build()
        val clients = api.getClientList(request).clientsList

        _clients.update { clients.map { UserShort.fromProto(it) } }
    }
}