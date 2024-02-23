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
import com.example.hbofficerandroid.presentation.MainViewModel
import com.example.hbofficerandroid.presentation.navigation.Screen
import com.example.hbofficerandroid.presentation.theme.AppTheme

@Composable
fun BottomNavBar(active: Screen, onClick: (Screen) -> Unit) {
    NavigationBar {
        MainViewModel.SCREENS.forEach { item ->
            NavBarItem(
                item = item,
                selected = active == item,
                onClick = remember { { onClick(item) } }
            )
        }
    }
}

@Composable
private fun RowScope.NavBarItem(item: Screen, selected: Boolean, onClick: () -> Unit) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = { NavBarIcon(item, selected) },
        label = { NavBarText(item.title) },
    )
}

@Composable
private fun NavBarIcon(item: Screen, selected: Boolean) {
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
                active = Screen.Main,
                onClick = {}
            )
        }
    }
}