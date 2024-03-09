package com.example.trbofficerandroid.presentation.ui.screen.addtariff

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.common.SnackbarError
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddTariffScreen(
    onBackClick: () -> Unit,
) {
    val viewModel: AddTariffViewModel = koinViewModel()
    val name by viewModel.name.collectAsState()
    val interestRate by viewModel.interestRate.collectAsState()
    val description by viewModel.description.collectAsState()
    val shackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.navigateBack.collect {
            onBackClick()
        }
    }

    LaunchedEffect(true) {
        viewModel.error.collect {
            shackBarHostState.showSnackbar(it)
        }
    }

    AddTariffScreenContent(
        name = name,
        interestRate = interestRate,
        description = description,
        loading = viewModel.loading.collectAsState().value,
        shackBarHostState = shackBarHostState,

        onNameChange = remember { { viewModel.onNameChange(it) } },
        onInterestRateChange = remember { { viewModel.onInterestRateChange(it) } },
        onDescriptionChange = remember { { viewModel.onDescriptionChange(it) } },
        onBackClick = remember { { viewModel.onBackClick() } },
        onAddClick = remember { { viewModel.onAddTariff() } }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddTariffScreenContent(
    name: String = "",
    interestRate: String = "",
    description: String = "",
    loading: Boolean = false,
    shackBarHostState: SnackbarHostState = remember { SnackbarHostState() },

    onNameChange: (String) -> Unit = {},
    onInterestRateChange: (String) -> Unit = {},
    onDescriptionChange: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = { BackButton(onBackClick) },
                title = { Text(text = "Новый тариф") },
                actions = {
                    AnimatedVisibility(visible = loading, enter = scaleIn(), exit = scaleOut()) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp,
                            strokeCap = StrokeCap.Round
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = shackBarHostState, snackbar = { SnackbarError(it) })
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

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Описание")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = description,
                    onValueChange = onDescriptionChange
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onAddClick() }
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