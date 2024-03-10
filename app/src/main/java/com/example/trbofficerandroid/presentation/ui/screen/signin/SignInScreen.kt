package com.example.trbofficerandroid.presentation.ui.screen.signin

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.R
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.SnackbarError
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(navigateToHome: () -> Unit) {
    val viewModel: SignInViewModel = koinViewModel()
    val shackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.navigateToHome.collect {
            navigateToHome()
        }
    }

    LaunchedEffect(true) {
        viewModel.error.collect {
            shackBarHostState.showSnackbar(it)
        }
    }

    SignInScreenContent(
        email = viewModel.email.collectAsState().value,
        password = viewModel.password.collectAsState().value,
        loading = viewModel.loading.collectAsState().value,
        shackBarHostState = shackBarHostState,

        onEmailChange = remember { { viewModel.onEmailChange(it) } },
        onPasswordChange = remember { { viewModel.onPasswordChange(it) } },
        onSignInClick = remember { { viewModel.signIn() } }
    )
}

@Composable
private fun SignInScreenContent(
    email: String,
    password: String,
    loading: Boolean = false,
    shackBarHostState: SnackbarHostState = remember { SnackbarHostState() },

    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onSignInClick: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier
            .imePadding()
            .animateContentSize(),
        snackbarHost = {
            SnackbarHost(hostState = shackBarHostState, snackbar = { SnackbarError(it) })
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.weight(0.5f))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "E-mail") },
                value = email,
                onValueChange = onEmailChange,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Пароль") },
                value = password,
                onValueChange = onPasswordChange,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            )
            Column(
                modifier = Modifier
                    .defaultMinSize(48.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                if (loading) {
                    CircularProgressIndicator()
                }
                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier.fillMaxWidth(), onClick = onSignInClick) {
                Text(text = "Войти")
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
            SignInScreenContent(
                email = "asdasdasd@gmail.com",
                password = "password",
            )
        }
    }
}