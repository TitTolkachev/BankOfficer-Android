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
            LoanListScreen(
                navigateToLoan = {
                    rootNavController.navigate("${Screen.Loan.route}/$it") {
                        launchSingleTop = true
                    }
                },
                navigateToApplication = {
                    rootNavController.navigate("${Screen.Application.route}/$it") {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(
            route = HomeScreen.UserList.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
        ) {
            UserListScreen(
                fabActions = fabActions,
                onClientClick = {
                    rootNavController.navigate("${Screen.Client.route}/$it") {
                        launchSingleTop = true
                    }
                },
                onOfficerClick = {
                    rootNavController.navigate("${Screen.Officer.route}/$it") {
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
            route = HomeScreen.TariffList.route,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
        ) {
            TariffListScreen(
                fabActions = fabActions,
                onTariffClick = {
                    with(it) {
                        rootNavController.navigate("${Screen.Tariff.route}/$id/$additionDate/$name/$description/$interestRate/$officerId") {
                            launchSingleTop = true
                        }
                    }
                },
                onAddTariffClick = {
                    rootNavController.navigate(Screen.AddTariff.route) {
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
            MoreScreen(
                navigateToSignIn = {
                    rootNavController.popBackStack()
                    rootNavController.navigate(Screen.SignIn.route) {
                        launchSingleTop = true
                    }
                }
            )
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