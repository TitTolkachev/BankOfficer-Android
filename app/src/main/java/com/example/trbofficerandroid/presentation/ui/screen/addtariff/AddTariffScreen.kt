package com.example.trbofficerandroid.presentation.ui.screen.addtariff

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddTariffScreen(
    onBackClick: () -> Unit,
) {
    val viewModel: AddTariffViewModel = koinViewModel()
    val name by viewModel.name.collectAsState()
    val interestRate by viewModel.interestRate.collectAsState()

    AddTariffScreenContent(
        name = name,
        interestRate = interestRate,

        onNameChange = remember { { viewModel.onNameChange(it) } },
        onInterestRateChange = remember { { viewModel.onInterestRateChange(it) } },
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddTariffScreenContent(
    name: String = "",
    interestRate: String = "",

    onNameChange: (String) -> Unit = {},
    onInterestRateChange: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = { BackButton(onBackClick) },
                title = { Text(text = "Новый тариф") }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = "Название")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = onNameChange,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Процентная ставка, %")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = interestRate,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                onValueChange = onInterestRateChange,
                singleLine = true
            )

            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ onBackClick() }
            ) {
                Text(text = "Создать")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            AddTariffScreenContent()
        }
    }
}