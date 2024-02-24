package com.example.hbofficerandroid.presentation.navigation

sealed class Screen(val route: String) {

    data object Home : Screen("home")
    data object Rate : Screen("rate")
    data object Client : Screen("client")
    data object Officer : Screen("officer")
    data object AddUser : Screen("add_user")
}