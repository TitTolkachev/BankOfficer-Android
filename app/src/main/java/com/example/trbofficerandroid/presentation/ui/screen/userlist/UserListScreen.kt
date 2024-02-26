package com.example.trbofficerandroid.presentation.ui.screen.userlist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.trbofficerandroid.R
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.screen.userlist.components.ClientListItem
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState.CLIENT
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserListTabState.OFFICER
import com.example.trbofficerandroid.presentation.ui.screen.userlist.model.UserShort
import kotlinx.coroutines.flow.SharedFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserListScreen(
    fabActions: SharedFlow<Unit>,
    onClientClick: () -> Unit,
    onOfficerClick: () -> Unit,
    onAddUserClick: () -> Unit,
) {
    val viewModel: UserListViewModel = koinViewModel()
    val activeTab by viewModel.activeTab.collectAsState()
    val searchActive by viewModel.searchActive.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    // Fab Actions Handling
    LaunchedEffect(Unit) {
        fabActions.collect {
            onAddUserClick()
        }
    }

    UserListScreenContent(
        activeTab = activeTab,
        searchActive = searchActive,
        searchQuery = searchQuery,

        onClientClick = onClientClick,
        onOfficerClick = onOfficerClick,
        onTabStateChange = remember { { viewModel.onTabStateChange(it) } },
        onSearchBarStateChange = remember { { viewModel.onSearchBarStateChange(it) } },
        onSearchQueryChange = remember { { viewModel.onSearchQueryChange(it) } },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserListScreenContent(
    activeTab: UserListTabState = CLIENT,
    searchActive: Boolean = false,
    searchQuery: String = "",

    onClientClick: () -> Unit = {},
    onOfficerClick: () -> Unit = {},
    onTabStateChange: (UserListTabState) -> Unit = {},
    onSearchBarStateChange: (Boolean) -> Unit = {},
    onSearchQueryChange: (String) -> Unit = {},
) {

    val clients = listOf(
        UserShort("1", "Иван1", "Иванов", "01.01.2020"),
        UserShort("2", "Иван2", "Иванов", "01.01.2020"),
        UserShort("3", "Иван3", "Иванов", "01.01.2020"),
        UserShort("4", "Иван4", "Иванов", "01.01.2020"),
    )

    val officers = listOf(
        UserShort("1", "Архимед", "Иванов", "01.01.1999"),
        UserShort("2", "Юлий", "Цезарь", "01.01.1999"),
        UserShort("3", "Аристотель", "Иванов", "01.01.1999"),
        UserShort("4", "Гараж", "Продам", "01.01.1999"),
        UserShort("5", "Максим", "Толокнов", "01.01.1999"),
        UserShort("6", "Константин", "Самойленко", "01.01.1999"),
    )

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

        PrimaryTabRow(selectedTabIndex = activeTab.ordinal) {
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
                LazyColumn {
                    items(items = clients, key = { it.id }) {
                        ClientListItem(item = it, onClick = onClientClick)
                    }
                }
            }

            OFFICER -> {
                LazyColumn {
                    items(items = officers, key = { it.id }) {
                        ClientListItem(item = it, onClick = onOfficerClick)
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
            UserListScreenContent()
        }
    }
}