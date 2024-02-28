package com.example.trbofficerandroid.presentation.navigation

sealed class Screen(val route: String) {

    data object Home : Screen("home")
    data object Rate : Screen("rate")
    data object Client : Screen("client")
    data object Officer : Screen("officer")
    data object AddClient : Screen("add_client")
    data object AddOfficer : Screen("add_officer")
    data object AddRate : Screen("add_rate")
    data object Account : Screen("account")
}