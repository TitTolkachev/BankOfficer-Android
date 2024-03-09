package com.example.trbofficerandroid.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trbofficerandroid.presentation.ui.screen.loanlist.LoanListScreen
import com.example.trbofficerandroid.presentation.ui.screen.main.MainScreen
import com.example.trbofficerandroid.presentation.ui.screen.more.MoreScreen
import com.example.trbofficerandroid.presentation.ui.screen.tarifflist.TariffListScreen
import com.example.trbofficerandroid.presentation.ui.screen.userlist.UserListScreen
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    rootNavController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    fabActions: SharedFlow<Unit>
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier.fillMaxSize()
    ) {
        composable(
            route = HomeScreen.Main.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
        ) {
            MainScreen()
        }
        composable(
            route = HomeScreen.LoanList.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
        ) {
            LoanListScreen()
        }
        composable(
            route = HomeScreen.UserList.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
        ) {
            UserListScreen(
                fabActions = fabActions,
                onClientClick = {
                    rootNavController.navigate(Screen.Client.route) {
                        launchSingleTop = true
                    }
                },
                onOfficerClick = {
                    rootNavController.navigate(Screen.Officer.route) {
                        launchSingleTop = true
                    }
                },
                onAddClientClick = {
                    rootNavController.navigate(Screen.AddClient.route) {
                        launchSingleTop = true
                    }
                },
                onAddOfficerClick = {
                    rootNavController.navigate(Screen.AddOfficer.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(
            route = HomeScreen.RateList.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
        ) {
            TariffListScreen(
                fabActions = fabActions,
                onRateClick = {
                    rootNavController.navigate(Screen.Rate.route) {
                        launchSingleTop = true
                    }
                },
                onAddRateClick = {
                    rootNavController.navigate(Screen.AddRate.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(
            route = HomeScreen.More.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
        ) {
            MoreScreen()
        }
    }
}

/**
 * Shows transition animation after navigating to screen.
 */
private fun enterTransition(): EnterTransition {
    return EnterTransition.None
}

/**
 * Shows transition animation after navigating from screen.
 */
private fun exitTransition(): ExitTransition {
    return ExitTransition.None
}