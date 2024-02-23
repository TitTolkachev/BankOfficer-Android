package com.example.hbofficerandroid.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.hbofficerandroid.presentation.navigation.NavGraph
import com.example.hbofficerandroid.presentation.theme.AppTheme
import com.example.hbofficerandroid.presentation.ui.common.BottomNavBar
import com.example.hbofficerandroid.presentation.ui.common.Fab
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val viewModel: MainViewModel = koinViewModel()
                val navController = rememberNavController()
                val activeScreen by viewModel.activeScreen.collectAsState()
                val fabVisible by viewModel.fabVisible.collectAsState()

                // Bottom Navigation Actions
                LaunchedEffect(Unit) {
                    viewModel.bottomNavActions.collect {
                        navController.navigate(it) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }

                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
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
                    NavGraph(
                        navController = navController,
                        startDestination = MainViewModel.START_SCREEN.route,
                        modifier = Modifier.padding(paddingValues),
                        fabActions = viewModel.fabActions
                    )
                }
            }
        }
    }
}