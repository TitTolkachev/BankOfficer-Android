package com.example.trbofficerandroid.presentation.ui.screen.addclient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.screen.addclient.model.AddClientShort
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddClientScreen(
    onBackClick: () -> Unit
) {
    val viewModel: AddClientViewModel = koinViewModel()
    val user by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navigateBack.collect {
            onBackClick()
        }
    }

    AddClientScreenContent(
        user = user,

        onFirstNameChange = remember { { viewModel.onFirstNameChange(it) } },
        onLastNameChange = remember { { viewModel.onLastNameChange(it) } },
        onPatronymicNameChange = remember { { viewModel.onPatronymicNameChange(it) } },
        onPhoneNumberChange = remember { { viewModel.onPhoneNumberChange(it) } },
        onAddressChange = remember { { viewModel.onAddressChange(it) } },
        onPassportNumberChange = remember { { viewModel.onPassportNumberChange(it) } },
        onPassportSeriesChange = remember { { viewModel.onPassportSeriesChange(it) } },
        onEmailChange = remember { { viewModel.onEmailChange(it) } },
        onPasswordChange = remember { { viewModel.onPasswordChange(it) } },
        onBackClick = onBackClick,
        onCreateClick = remember { { viewModel.onCreateUser() } },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddClientScreenContent(
    user: AddClientShort,

    onFirstNameChange: (String) -> Unit = {},
    onLastNameChange: (String) -> Unit = {},
    onPatronymicNameChange: (String) -> Unit = {},
    onPhoneNumberChange: (String) -> Unit = {},
    onAddressChange: (String) -> Unit = {},
    onPassportNumberChange: (String) -> Unit = {},
    onPassportSeriesChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    onCreateClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Новый клиент") },
                navigationIcon = { BackButton(onBackClick) }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                // Person
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    text = "Информация"
                )
                Spacer(Modifier.height(4.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Фамилия") },
                    value = user.lastName,
                    onValueChange = onLastNameChange,
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Имя") },
                    value = user.firstName,
                    onValueChange = onFirstNameChange,
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Отчество") },
                    value = user.patronymicName,
                    onValueChange = onPatronymicNameChange,
                    singleLine = true
                )

                // Data
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Телефон") },
                    value = user.phoneNumber,
                    onValueChange = onPhoneNumberChange,
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Адрес") },
                    value = user.address,
                    onValueChange = onAddressChange,
                    singleLine = true
                )

                // Document
                Spacer(Modifier.height(16.dp))
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    text = "Паспорт"
                )
                Spacer(Modifier.height(4.dp))
                Row {
                    Column(Modifier.weight(1f)) {
                        Text(text = "Серия")
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "XX XX") },
                            value = user.passportSeries,
                            onValueChange = onPassportSeriesChange,
                            singleLine = true
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                    Column(Modifier.weight(1f)) {
                        Text(text = "Номер")
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "XXX XXX") },
                            value = user.passportNumber,
                            onValueChange = onPassportNumberChange,
                            singleLine = true
                        )
                    }
                }

                // Email and Password
                Spacer(Modifier.height(16.dp))
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    text = "Данные для входа"
                )
                Spacer(Modifier.height(4.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "E-mail") },
                    value = user.email,
                    onValueChange = onEmailChange,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Пароль") },
                    value = user.password,
                    onValueChange = onPasswordChange,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier.fillMaxWidth(), onClick = onCreateClick) {
                Text(text = "Создать")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            AddClientScreenContent(
                user = AddClientShort(
                    firstName = "First Name",
                    lastName = "Last Name",
                    patronymicName = "Patronymic Name",
                    phoneNumber = "8800-555-35-35",
                    address = "Address",
                    passportNumber = "12 12 123123",
                    passportSeries = "12 21",
                    email = "",
                    password = "",
                )
            )
        }
    }
}