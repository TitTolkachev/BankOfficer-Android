package com.example.bankofficer.presentation.navigation

sealed class Screen(val route: String) {
    data object Main : Screen(route = "main")
}