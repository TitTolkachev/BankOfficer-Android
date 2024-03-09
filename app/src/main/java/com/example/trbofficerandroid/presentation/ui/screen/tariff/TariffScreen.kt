package com.example.trbofficerandroid.presentation.ui.screen.tariff

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun TariffScreen(
    navigateBack: () -> Unit,
    navigateToOfficer: (String?) -> Unit,
) {
    val viewModel: TariffViewModel = koinViewModel()

    TariffScreenContent(
        id = viewModel.id,
        additionDate = viewModel.additionDate,
        name = viewModel.name,
        description = viewModel.description,
        interestRate = viewModel.interestRate,

        onBackClick = navigateBack,
        onShowOfficerClick = { navigateToOfficer(viewModel.officerId) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TariffScreenContent(
    id: String,
    additionDate: String,
    name: String,
    description: String,
    interestRate: String,

    onBackClick: () -> Unit = {},
    onShowOfficerClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = { BackButton(onBackClick) },
                title = { Text(text = "Просмотр тарифа") }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = "Id")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = id,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Дата создания")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = additionDate,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Название")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Процентная ставка, %")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = interestRate,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Описание")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    value = description,
                    onValueChange = {}
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onShowOfficerClick
            ) {
                Text(text = "Показать сотрудника")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            TariffScreenContent(
                id = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                additionDate = "11.11.2011",
                name = "Name",
                description = "Description",
                interestRate = "123.0%",
            )
        }
    }
}