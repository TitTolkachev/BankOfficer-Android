package com.example.trbofficerandroid.presentation.ui.screen.addofficer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import com.example.trbofficerandroid.domain.model.Sex
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.BackButton
import com.example.trbofficerandroid.presentation.ui.common.SnackbarError
import com.example.trbofficerandroid.presentation.ui.screen.addofficer.model.AddOfficer
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOfficerScreen(
    onBackClick: () -> Unit
) {
    val viewModel: AddOfficerViewModel = koinViewModel()
    val user by viewModel.user.collectAsState()
    val datePickerVisible by viewModel.datePickerVisible.collectAsState()
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

    if (datePickerVisible) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { viewModel.hideDatePicker() },
            dismissButton = {
                TextButton(onClick = { viewModel.hideDatePicker() }) {
                    Text(text = "Отменить")
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.onBirthDateChange(datePickerState.selectedDateMillis ?: 0L)
                    viewModel.hideDatePicker()
                }) {
                    Text(text = "Сохранить")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }

    AddOfficerScreenContent(
        user = user,
        loading = viewModel.loading.collectAsState().value,
        shackBarHostState = shackBarHostState,

        onFirstNameChange = remember { { viewModel.onFirstNameChange(it) } },
        onLastNameChange = remember { { viewModel.onLastNameChange(it) } },
        onPatronymicNameChange = remember { { viewModel.onPatronymicNameChange(it) } },
        onPhoneNumberChange = remember { { viewModel.onPhoneNumberChange(it) } },
        onAddressChange = remember { { viewModel.onAddressChange(it) } },
        onSexChange = remember { { viewModel.onSexChange(it) } },
        onPassportNumberChange = remember { { viewModel.onPassportNumberChange(it) } },
        onPassportSeriesChange = remember { { viewModel.onPassportSeriesChange(it) } },
        onEmailChange = remember { { viewModel.onEmailChange(it) } },
        onPasswordChange = remember { { viewModel.onPasswordChange(it) } },

        showDatePicker = remember { { viewModel.showDatePicker() } },

        onBackClick = onBackClick,
        onCreateClick = remember { { viewModel.onCreateUser() } },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddOfficerScreenContent(
    user: AddOfficer,
    loading: Boolean = false,
    shackBarHostState: SnackbarHostState = remember { SnackbarHostState() },

    onFirstNameChange: (String) -> Unit = {},
    onLastNameChange: (String) -> Unit = {},
    onPatronymicNameChange: (String) -> Unit = {},
    onPhoneNumberChange: (String) -> Unit = {},
    onAddressChange: (String) -> Unit = {},
    showDatePicker: () -> Unit = {},
    onSexChange: (Sex) -> Unit = {},
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
                title = { Text("Новый сотрудник") },
                navigationIcon = { BackButton(onBackClick) },
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
                .padding(horizontal = 16.dp)
                .fillMaxSize()
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
            Spacer(Modifier.height(8.dp))

            // Birth Date
            val dateInteractionSource = remember { MutableInteractionSource() }
            if (dateInteractionSource.collectIsPressedAsState().value) showDatePicker()
            OutlinedTextField(
                value = user.birthDate,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showDatePicker() },
                label = { Text(text = "Дата рождения") },
                readOnly = true,
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null
                    )
                },
                interactionSource = dateInteractionSource
            )

            // Sex
            Spacer(Modifier.height(16.dp))
            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                SegmentedButton(
                    selected = user.sex == Sex.MALE,
                    onClick = { onSexChange(Sex.MALE) },
                    shape = SegmentedButtonDefaults.itemShape(index = 0, count = 2)
                ) {
                    Text(text = "М")
                }
                SegmentedButton(
                    selected = user.sex == Sex.FEMALE,
                    onClick = { onSexChange(Sex.FEMALE) },
                    shape = SegmentedButtonDefaults.itemShape(index = 1, count = 2)
                ) {
                    Text(text = "Ж")
                }
            }

            // Document
            Spacer(Modifier.height(16.dp))
            Text(
                style = MaterialTheme.typography.headlineSmall,
                text = "Паспорт"
            )
            Spacer(Modifier.height(4.dp))
            Row {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    label = { Text(text = "Серия") },
                    value = user.passportSeries,
                    onValueChange = onPassportSeriesChange,
                    singleLine = true
                )
                Spacer(Modifier.width(16.dp))
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    label = { Text(text = "Номер") },
                    value = user.passportNumber,
                    onValueChange = onPassportNumberChange,
                    singleLine = true
                )
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
            AddOfficerScreenContent(
                user = AddOfficer(
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