package com.example.hbofficerandroid.presentation.navigation

import com.example.hbofficerandroid.R

sealed class Screen(val route: String, val icon: Int, val title: String) {
    data object Main : Screen(
        route = "main", icon = R.drawable.ic_launcher_foreground, title = "1"
    )

    data object Screen1 : Screen(
        route = "main1", icon = R.drawable.ic_launcher_foreground, title = "2"
    )

    data object Screen2 : Screen(
        route = "main2", icon = R.drawable.ic_launcher_foreground, title = "3"
    )
}