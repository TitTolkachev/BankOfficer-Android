package com.example.hbofficerandroid.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hbofficerandroid.presentation.ui.screen.loanlist.LoanListScreen
import com.example.hbofficerandroid.presentation.ui.screen.main.MainScreen
import com.example.hbofficerandroid.presentation.ui.screen.more.MoreScreen
import com.example.hbofficerandroid.presentation.ui.screen.ratelist.RateListScreen
import com.example.hbofficerandroid.presentation.ui.screen.userlist.UserListScreen
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
            route = HomeScreen.Main.route
        ) {
            MainScreen()
        }
        composable(
            route = HomeScreen.LoanList.route
        ) {
            LoanListScreen()
        }
        composable(
            route = HomeScreen.UserList.route
        ) {
            UserListScreen(
                fabActions = fabActions,
                onClientClick = {
                    rootNavController.navigate(Screen.Client.route)
                },
                onOfficerClick = {
                    rootNavController.navigate(Screen.Officer.route)
                },
                onAddUserClick = {
                    rootNavController.navigate(Screen.AddUser.route)
                }
            )
        }
        composable(
            route = HomeScreen.RateList.route
        ) {
            RateListScreen()
        }
        composable(
            route = HomeScreen.More.route
        ) {
            MoreScreen()
        }
    }
}
