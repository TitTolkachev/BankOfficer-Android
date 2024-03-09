package com.example.trbofficerandroid.presentation.ui.screen.userlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.trbofficerandroid.R
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.screen.userlist.components.UserListBottomSheet
import com.example.trbofficerandroid.presentation.ui.screen.userlist.components.UserListItem
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState.CLIENT
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState.OFFICER
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserShort
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserListScreen(
    fabActions: SharedFlow<Unit>,
    onClientClick: (String) -> Unit,
    onOfficerClick: (String) -> Unit,
    onAddClientClick: () -> Unit,
    onAddOfficerClick: () -> Unit,
) {
    val viewModel: UserListViewModel = koinViewModel()
    val clients by viewModel.clients.collectAsState()
    val officers by viewModel.officers.collectAsState()
    val activeTab by viewModel.activeTab.collectAsState()
    val searchActive by viewModel.searchActive.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    // Choosing a user type
    var showBottomSheet by remember { mutableStateOf(false) }
    UserListBottomSheet(
        showBottomSheet = showBottomSheet,
        hideBottomSheet = remember { { showBottomSheet = false } },
        onAddClientClick = onAddClientClick,
        onAddOfficerClick = onAddOfficerClick,
    )

    // Fab Actions Handling
    LaunchedEffect(Unit) {
        fabActions.collect {
            showBottomSheet = true
        }
    }

    UserListScreenContent(
        clients = clients,
        officers = officers,
        activeTab = activeTab,
        searchActive = searchActive,
        searchQuery = searchQuery,

        onClientClick = onClientClick,
        onOfficerClick = onOfficerClick,
        onTabStateChange = remember { { viewModel.onTabStateChange(it) } },
        onSearchBarStateChange = remember { { viewModel.onSearchBarStateChange(it) } },
        onSearchQueryChange = remember { { viewModel.onSearchQueryChange(it) } },

        refreshClients = viewModel::loadClients,
        refreshOfficers = viewModel::loadOfficers
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserListScreenContent(
    clients: List<UserShort>? = listOf(),
    officers: List<UserShort>? = listOf(),
    activeTab: UserListTabState = CLIENT,
    searchActive: Boolean = false,
    searchQuery: String = "",

    onClientClick: (String) -> Unit = {},
    onOfficerClick: (String) -> Unit = {},
    onTabStateChange: (UserListTabState) -> Unit = {},
    onSearchBarStateChange: (Boolean) -> Unit = {},
    onSearchQueryChange: (String) -> Unit = {},

    refreshClients: suspend () -> Unit = {},
    refreshOfficers: suspend () -> Unit = {},
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SearchBar(
            placeholder = { Text(text = "Найти пользователя") },
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
                selected = activeTab == CLIENT,
                onClick = { onTabStateChange(CLIENT) },
                text = { Text("Клиенты") },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.self_improvement_24),
                        contentDescription = null
                    )
                })

            Tab(
                selected = activeTab == OFFICER,
                onClick = { onTabStateChange(OFFICER) },
                text = { Text("Сотрудники") },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.groups_filled_24),
                        contentDescription = null
                    )
                })
        }

        when (activeTab) {
            CLIENT -> {
                if (clients == null) {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                } else {
                    val listState = rememberLazyListState()
                    val refreshState = rememberPullToRefreshState()
                    if (refreshState.isRefreshing) {
                        LaunchedEffect(true) {
                            refreshClients()
                            delay(500L)
                            listState.animateScrollToItem(0)
                            delay(500L)
                            refreshState.endRefresh()
                        }
                    }
                    Box(Modifier.nestedScroll(refreshState.nestedScrollConnection)) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = listState,
                            contentPadding = PaddingValues(bottom = 24.dp)
                        ) {
                            items(items = clients, key = { it.id }) {
                                UserListItem(item = it, onClick = { onClientClick(it.id) })
                            }
                        }
                        PullToRefreshContainer(
                            modifier = Modifier.align(Alignment.TopCenter),
                            state = refreshState,
                        )
                    }
                }
            }

            OFFICER -> {
                if (officers == null) {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                } else {
                    val listState = rememberLazyListState()
                    val refreshState = rememberPullToRefreshState()
                    if (refreshState.isRefreshing) {
                        LaunchedEffect(true) {
                            refreshOfficers()
                            delay(500L)
                            listState.animateScrollToItem(0)
                            delay(500L)
                            refreshState.endRefresh()
                        }
                    }
                    Box(Modifier.nestedScroll(refreshState.nestedScrollConnection)) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            state = listState,
                            contentPadding = PaddingValues(bottom = 24.dp)
                        ) {
                            items(items = officers, key = { it.id }) {
                                UserListItem(item = it, onClick = { onOfficerClick(it.id) })
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

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            UserListScreenContent(
                activeTab = OFFICER,
                officers = listOf(
                    UserShort("1", "Архимед", "Иванов", "01.01.1999"),
                    UserShort("2", "Юлий", "Цезарь", "01.01.1999"),
                    UserShort("3", "Аристотель", "Иванов", "01.01.1999"),
                    UserShort("4", "Гараж", "Продам", "01.01.1999"),
                    UserShort("5", "Максим", "Толокнов", "01.01.1999"),
                    UserShort("6", "Константин", "Самойленко", "01.01.1999"),
                )
            )
        }
    }
}