package com.example.hbofficerandroid.presentation.navigation

import com.example.hbofficerandroid.R

sealed class Screen(val route: String, val icon: Int, val iconFilled: Int, val title: String) {
    data object Main : Screen(
        route = "main",
        icon = R.drawable.account_balance_24,
        iconFilled = R.drawable.account_balance_filled_24,
        title = "Главная"
    )

    data object Loans : Screen(
        route = "loans",
        icon = R.drawable.payments_24,
        iconFilled = R.drawable.payments_filled_24,
        title = "Кредиты"
    )

    data object Users : Screen(
        route = "users",
        icon = R.drawable.person_search_24,
        iconFilled = R.drawable.person_search_filled_24,
        title = "Люди"
    )

    data object Rates : Screen(
        route = "rates",
        icon = R.drawable.readiness_score_24,
        iconFilled = R.drawable.readiness_score_filled_24,
        title = "Тарифы"
    )

    data object More : Screen(
        route = "more",
        icon = R.drawable.more_vert_24,
        iconFilled = R.drawable.more_vert_filled_24,
        title = "Еще"
    )
}