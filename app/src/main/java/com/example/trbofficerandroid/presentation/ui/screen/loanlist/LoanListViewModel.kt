package com.example.trbofficerandroid.presentation.ui.screen.loanlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trbofficerandroid.domain.model.ApplicationShort
import com.example.trbofficerandroid.domain.model.LoanShort
import com.example.trbofficerandroid.domain.usecase.GetApplicationListUseCase
import com.example.trbofficerandroid.domain.usecase.GetLoanListUseCase
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.model.LoanListTabState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoanListViewModel(
    private val getLoanListUseCase: GetLoanListUseCase,
    private val getApplicationListUseCase: GetApplicationListUseCase,
) : ViewModel() {

    private val _loanList: MutableStateFlow<List<LoanShort>?> = MutableStateFlow(null)
    val loanList = _loanList.asStateFlow()

    private val _applicationList: MutableStateFlow<List<ApplicationShort>?> = MutableStateFlow(null)
    val applicationList = _applicationList.asStateFlow()

    private val _searchActive = MutableStateFlow(false)
    val searchActive: StateFlow<Boolean> = _searchActive.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _activeTab = MutableStateFlow(LoanListTabState.LOANS)
    val activeTab: StateFlow<LoanListTabState> = _activeTab.asStateFlow()

    private val _showLoadFailure = MutableSharedFlow<Unit>()
    val showLoadFailure = _showLoadFailure.asSharedFlow()

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        launch { loadLoans() }
        launch { loadApplications() }
    }

    suspend fun loadLoans() {
        val loans = try {
            getLoanListUseCase()
        } catch (_: Exception) {
            _showLoadFailure.emit(Unit)
            null
        }
        if (_loanList.value == null && loans == null) _loanList.update { emptyList() }
        else if (loans != null) _loanList.update { loans }
    }

    suspend fun loadApplications() {
        val applications = try {
            getApplicationListUseCase()
        } catch (_: Exception) {
            _showLoadFailure.emit(Unit)
            null
        }
        if (_applicationList.value == null && applications == null) _applicationList.update { emptyList() }
        else if (applications != null) _applicationList.update { applications }
    }

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