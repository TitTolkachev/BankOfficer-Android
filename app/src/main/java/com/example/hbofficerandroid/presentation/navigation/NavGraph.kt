package com.example.hbofficerandroid.presentation.navigation

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
import com.example.hbofficerandroid.presentation.ui.screen.loans.LoansScreen
import com.example.hbofficerandroid.presentation.ui.screen.main.MainScreen
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun NavGraph(
    navController: NavHostController,
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
            route = Screen.Main.route
        ) {
            MainScreen(fabActions)
        }
        composable(
            route = Screen.Loans.route
        ) {
            LoansScreen(fabActions)
        }
        composable(
            route = Screen.Users.route
        ) {
            MainScreen(fabActions)
        }
        composable(
            route = Screen.Rates.route
        ) {
            MainScreen(fabActions)
        }
        composable(
            route = Screen.More.route
        ) {
            MainScreen(fabActions)
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