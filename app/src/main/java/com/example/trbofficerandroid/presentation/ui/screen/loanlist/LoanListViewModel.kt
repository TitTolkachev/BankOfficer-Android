package com.example.trbofficerandroid.presentation.ui.screen.loanlist

import androidx.lifecycle.ViewModel
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.model.LoanListTabState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoanListViewModel : ViewModel() {

    private val _searchActive = MutableStateFlow(false)
    val searchActive: StateFlow<Boolean> = _searchActive.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _activeTab = MutableStateFlow(LoanListTabState.LOANS)
    val activeTab: StateFlow<LoanListTabState> = _activeTab.asStateFlow()

    fun onTabStateChange(state: LoanListTabState) {
        _activeTab.update { state }
    }

    fun onSearchBarStateChange(active: Boolean) {
        _searchActive.update { active }
    }

    fun onSearchQueryChange(value: String) {
        _searchQuery.update { value }
    }
}