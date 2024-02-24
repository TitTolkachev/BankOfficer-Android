package com.example.trbofficerandroid.presentation.navigation

import com.example.trbofficerandroid.R

sealed class HomeScreen(val route: String, val icon: Int, val iconFilled: Int, val title: String) {
    data object Main : HomeScreen(
        route = "main",
        icon = R.drawable.account_balance_24,
        iconFilled = R.drawable.account_balance_filled_24,
        title = "Главная"
    )

    data object LoanList : HomeScreen(
        route = "loans",
        icon = R.drawable.payments_24,
        iconFilled = R.drawable.payments_filled_24,
        title = "Кредиты"
    )

    data object UserList : HomeScreen(
        route = "users",
        icon = R.drawable.person_search_24,
        iconFilled = R.drawable.person_search_filled_24,
        title = "Люди"
    )

    data object RateList : HomeScreen(
        route = "rates",
        icon = R.drawable.readiness_score_24,
        iconFilled = R.drawable.readiness_score_filled_24,
        title = "Тарифы"
    )

    data object More : HomeScreen(
        route = "more",
        icon = R.drawable.more_vert_24,
        iconFilled = R.drawable.more_vert_filled_24,
        title = "Еще"
    )
}