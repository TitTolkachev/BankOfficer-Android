package com.example.trbofficerandroid.presentation.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trbofficerandroid.presentation.navigation.HomeNavGraph
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BottomNavBar
import com.example.trbofficerandroid.presentation.ui.common.Fab
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    rootNavController: NavHostController
) {
    val viewModel: HomeViewModel = koinViewModel()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val activeScreen by viewModel.activeScreen.collectAsState()
    val fabVisible by viewModel.fabVisible.collectAsState()

    // Bottom Navigation Actions
    LaunchedEffect(Unit) {
        viewModel.bottomNavActions.collect { route ->
            navController.navigate(route) {
                navController.graph.startDestinationRoute?.let { startRoute ->
                    popUpTo(startRoute) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    // Active Screen Handling
    LaunchedEffect(backStackEntry) {
        viewModel.onActiveScreenChange(backStackEntry?.destination?.route)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Fab(
                visible = fabVisible,
                onClick = remember { { viewModel.onFabClick() } }
            )
        },
        bottomBar = {
            BottomNavBar(
                active = activeScreen,
                onClick = remember { { viewModel.onBottomNavItemClick(it) } }
            )
        }
    ) { paddingValues ->
        HomeNavGraph(
            navController = navController,
            rootNavController = rootNavController,
            startDestination = HomeViewModel.START_SCREEN.route,
            modifier = Modifier.padding(paddingValues),
            fabActions = viewModel.fabActions
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            HomeScreen(rememberNavController())
        }
    }
}