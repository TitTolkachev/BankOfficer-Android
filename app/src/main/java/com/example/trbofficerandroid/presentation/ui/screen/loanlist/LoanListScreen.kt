package com.example.trbofficerandroid.presentation.ui.screen.loanlist

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.trbofficerandroid.R
import com.example.trbofficerandroid.domain.model.ApplicationShort
import com.example.trbofficerandroid.domain.model.LoanShort
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.components.ApplicationListItem
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.components.LoanListItem
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.model.LoanListTabState
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoanListScreen(
    navigateToLoan: (String) -> Unit,
    navigateToApplication: (String) -> Unit,
) {
    val viewModel: LoanListViewModel = koinViewModel()
    val activeTab by viewModel.activeTab.collectAsState()
    val searchActive by viewModel.searchActive.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.showLoadFailure.collect {
            Toast.makeText(context, "Нет ответа от сервера", Toast.LENGTH_SHORT).show()
        }
    }

    LoanListScreenContent(
        loans = viewModel.loanList.collectAsState().value,
        applications = viewModel.applicationList.collectAsState().value,
        activeTab = activeTab,
        searchActive = searchActive,
        searchQuery = searchQuery,

        onLoanClick = navigateToLoan,
        onApplicationClick = navigateToApplication,
        onTabStateChange = remember { { viewModel.onTabStateChange(it) } },
        onSearchBarStateChange = remember { { viewModel.onSearchBarStateChange(it) } },
        onSearchQueryChange = remember { { viewModel.onSearchQueryChange(it) } },

        refreshLoans = viewModel::loadLoans,
        refreshApplications = viewModel::loadApplications,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoanListScreenContent(
    loans: List<LoanShort>? = listOf(),
    applications: List<ApplicationShort>? = listOf(),
    activeTab: LoanListTabState = LoanListTabState.LOANS,
    searchActive: Boolean = false,
    searchQuery: String = "",

    onLoanClick: (String) -> Unit = {},
    onApplicationClick: (String) -> Unit = {},
    onTabStateChange: (LoanListTabState) -> Unit = {},
    onSearchBarStateChange: (Boolean) -> Unit = {},
    onSearchQueryChange: (String) -> Unit = {},

    refreshLoans: suspend () -> Unit = {},
    refreshApplications: suspend () -> Unit = {},
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SearchBar(
            placeholder = { Text(text = "Найти сумму") },
            query = searchQuery,
            onQueryChange = onSearchQueryChange,
            onSearch = {},
            active = searchActive,
            onActiveChange = { onSearchBarStateChange(it) },
            leadingIcon = {
                AnimatedVisibility(visible = searchActive, enter = scaleIn(), exit = scaleOut()) {
                    BackButton { onSearchBarStateChange(false) }
                }
            },
            trailingIcon = {
                AnimatedVisibility(visible = !searchActive, enter = scaleIn(), exit = scaleOut()) {
                    IconButton(onClick = { onSearchBarStateChange(true) }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                }
            }
        ) {
            // TODO
        }

        PrimaryTabRow(modifier = Modifier.zIndex(100f), selectedTabIndex = activeTab.ordinal) {
            Tab(
                selected = activeTab == LoanListTabState.LOANS,
                onClick = { onTabStateChange(LoanListTabState.LOANS) },
                text = { Text("Оформленные") },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.credit_card_filled_24),
                        contentDescription = null
                    )
                })

            Tab(
                selected = activeTab == LoanListTabState.APPLICATIONS,
                onClick = { onTabStateChange(LoanListTabState.APPLICATIONS) },
                text = { Text("Заявки") },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.request_quote_filled_24),
                        contentDescription = null
                    )
                })
        }

        when (activeTab) {
            LoanListTabState.LOANS -> {
                if (loans == null) {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                } else {
                    val listState = rememberLazyListState()
                    val refreshState = rememberPullToRefreshState()
                    if (refreshState.isRefreshing) {
                        LaunchedEffect(true) {
                            refreshLoans()
                            delay(500L)
                            listState.animateScrollToItem(0)
                            delay(500L)
                            refreshState.endRefresh()
                        }
                    }
                    Box(Modifier.nestedScroll(refreshState.nestedScrollConnection)) {
                        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
                            items(items = loans, key = { it.id }) {
                                LoanListItem(item = it, onClick = { onLoanClick(it.id) })
                            }
                        }
                        PullToRefreshContainer(
                            modifier = Modifier.align(Alignment.TopCenter),
                            state = refreshState,
                        )
                    }
                }
            }

            LoanListTabState.APPLICATIONS -> {
                if (applications == null) {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                } else {
                    val listState = rememberLazyListState()
                    val refreshState = rememberPullToRefreshState()
                    if (refreshState.isRefreshing) {
                        LaunchedEffect(true) {
                            refreshApplications()
                            delay(500L)
                            listState.animateScrollToItem(0)
                            delay(500L)
                            refreshState.endRefresh()
                        }
                    }
                    Box(Modifier.nestedScroll(refreshState.nestedScrollConnection)) {
                        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
                            items(items = applications, key = { it.id }) {
                                ApplicationListItem(
                                    item = it,
                                    onClick = { onApplicationClick(it.id) })
                            }
                        }
                        PullToRefreshContainer(
                            modifier = Modifier.align(Alignment.TopCenter),
                            state = refreshState,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        LoanListScreenContent()
    }
}