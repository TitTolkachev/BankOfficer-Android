package com.example.trbofficerandroid.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trbofficerandroid.presentation.ui.screen.addrate.AddRateScreen
import com.example.trbofficerandroid.presentation.ui.screen.adduser.AddUserScreen
import com.example.trbofficerandroid.presentation.ui.screen.client.ClientScreen
import com.example.trbofficerandroid.presentation.ui.screen.home.HomeScreen
import com.example.trbofficerandroid.presentation.ui.screen.officer.OfficerScreen
import com.example.trbofficerandroid.presentation.ui.screen.rate.RateScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier.fillMaxSize()
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(rootNavController = navController)
        }
        composable(
            route = Screen.Rate.route
        ) {
            RateScreen()
        }
        composable(
            route = Screen.Client.route
        ) {
            ClientScreen()
        }
        composable(
            route = Screen.Officer.route
        ) {
            OfficerScreen()
        }
        composable(
            route = Screen.AddUser.route
        ) {
            AddUserScreen()
        }
        composable(
            route = Screen.AddRate.route
        ) {
            AddRateScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

/**
 * Shows transition animation after navigating to screen.
 */
private fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition {
    return slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(400, easing = FastOutSlowInEasing)
    )
}

/**
 * Shows transition animation after navigating from screen.
 */
private fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition {
    return slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(300, easing = FastOutSlowInEasing)
    )
}