package com.example.trbofficerandroid.presentation.navigation

sealed class Screen(val route: String) {

    data object Home : Screen("home")
    data object Tariff : Screen("tariff")
    data object Client : Screen("client")
    data object Officer : Screen("officer")
    data object AddClient : Screen("add_client")
    data object AddOfficer : Screen("add_officer")
    data object AddTariff : Screen("add_tariff")
    data object Account : Screen("account")
    data object SignIn : Screen("sign_in")
}