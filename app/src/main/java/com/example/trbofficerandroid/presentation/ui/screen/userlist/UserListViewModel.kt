package com.example.trbofficerandroid.presentation.ui.screen.userlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.GetClientListRequest
import com.example.trbofficerandroid.UserServiceGrpc.UserServiceBlockingStub
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState.CLIENT
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserShort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val api: UserServiceBlockingStub
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

    private fun loadUsers() = viewModelScope.launch(Dispatchers.IO) {
        val request = GetClientListRequest.newBuilder().build()
        val clients = try {
            api.getClientList(request).clientsList
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "")
            emptyList()
        }
        _clients.update { clients.map { UserShort.fromProto(it) } }
    }

    companion object {
        private val TAG = UserListViewModel::class.simpleName
    }
}