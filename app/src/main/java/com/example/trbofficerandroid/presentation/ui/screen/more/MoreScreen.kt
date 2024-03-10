package com.example.trbofficerandroid.presentation.ui.screen.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.presentation.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoreScreen(navigateToSignIn: () -> Unit) {
    val viewModel: MoreViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.navigateToSignIn.collect {
            navigateToSignIn()
        }
    }

    MoreScreenContent(
        onLogoutClick = remember { { viewModel.logout() } }
    )
}

@Composable
private fun MoreScreenContent(
    onLogoutClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Spacer(Modifier.weight(1f))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLogoutClick,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text(text = "Выйти")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            MoreScreenContent()
        }
    }
}