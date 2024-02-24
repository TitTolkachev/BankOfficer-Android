package com.example.hbofficerandroid.presentation.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hbofficerandroid.presentation.navigation.HomeScreen
import com.example.hbofficerandroid.presentation.theme.AppTheme
import com.example.hbofficerandroid.presentation.ui.screen.home.HomeViewModel

@Composable
fun BottomNavBar(active: HomeScreen, onClick: (HomeScreen) -> Unit) {
    NavigationBar {
        HomeViewModel.SCREENS.forEach { item ->
            NavBarItem(
                item = item,
                selected = active == item,
                onClick = remember { { onClick(item) } }
            )
        }
    }
}

@Composable
private fun RowScope.NavBarItem(item: HomeScreen, selected: Boolean, onClick: () -> Unit) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = { NavBarIcon(item, selected) },
        label = { NavBarText(item.title) },
    )
}

@Composable
private fun NavBarIcon(item: HomeScreen, selected: Boolean) {
    Icon(
        modifier = Modifier.size(24.dp),
        painter = painterResource(id = if (selected) item.iconFilled else item.icon),
        contentDescription = item.title
    )
}

@Composable
private fun NavBarText(text: String) {
    Text(text = text)
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            BottomNavBar(
                active = HomeScreen.Main,
                onClick = {}
            )
        }
    }
}