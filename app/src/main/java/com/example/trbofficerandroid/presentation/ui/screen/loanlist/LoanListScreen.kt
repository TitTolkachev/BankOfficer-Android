package com.example.trbofficerandroid.presentation.ui.screen.loanlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.R
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.components.ApplicationListItem
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.components.LoanListItem
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.model.ApplicationShort
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.model.LoanListTabState
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.model.LoanShort
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.model.LoanStatus
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoanListScreen() {
    val viewModel: LoanListViewModel = koinViewModel()
    val activeTab by viewModel.activeTab.collectAsState()
    val searchActive by viewModel.searchActive.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    LoanListScreenContent(
        activeTab = activeTab,
        searchActive = searchActive,
        searchQuery = searchQuery,

        onLoanClick = {},
        onApplicationClick = {},
        onTabStateChange = remember { { viewModel.onTabStateChange(it) } },
        onSearchBarStateChange = remember { { viewModel.onSearchBarStateChange(it) } },
        onSearchQueryChange = remember { { viewModel.onSearchQueryChange(it) } },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoanListScreenContent(
    activeTab: LoanListTabState = LoanListTabState.LOANS,
    searchActive: Boolean = false,
    searchQuery: String = "",

    onLoanClick: () -> Unit = {},
    onApplicationClick: () -> Unit = {},
    onTabStateChange: (LoanListTabState) -> Unit = {},
    onSearchBarStateChange: (Boolean) -> Unit = {},
    onSearchQueryChange: (String) -> Unit = {},
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
                    IconButton(onClick = { onSearchBarStateChange(false) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
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

        PrimaryTabRow(selectedTabIndex = activeTab.ordinal) {
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

        val loans = listOf(
            LoanShort(
                "1",
                "First Name",
                "Last Name",
                100,
                0,
                LoanStatus.OPEN
            ),
            LoanShort(
                "2",
                "First Name",
                "Last Name",
                100,
                0,
                LoanStatus.OPEN
            ),
        )

        val applications = listOf(
            ApplicationShort(
                "1",
                4,
                100,
                11.1
            ),
            ApplicationShort(
                "2",
                1,
                1100,
                1.0
            ),
            ApplicationShort(
                "3",
                5,
                1000,
                19.19
            ),
        )

        when (activeTab) {
            LoanListTabState.LOANS -> {
                LazyColumn {
                    items(items = loans, key = { it.id }) {
                        LoanListItem(item = it, onClick = onLoanClick)
                    }
                }
            }

            LoanListTabState.APPLICATIONS -> {
                LazyColumn {
                    items(items = applications, key = { it.id }) {
                        ApplicationListItem(item = it, onClick = onApplicationClick)
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