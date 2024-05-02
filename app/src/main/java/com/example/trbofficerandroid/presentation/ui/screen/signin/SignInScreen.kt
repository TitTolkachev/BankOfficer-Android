package com.example.trbofficerandroid.presentation.ui.screen.signin

import android.content.Context
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trbofficerandroid.R
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.common.SnackbarError
import com.example.trbofficerandroid.presentation.ui.common.SnackbarSuccess
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(navigateToHome: () -> Unit) {
    val viewModel: SignInViewModel = koinViewModel()
    val shackBarHostState = remember { SnackbarHostState() }
    var isSnackBarMessageError by remember { mutableStateOf<Boolean?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                viewModel.signInWithGoogle(result.data)
            }
        }
    )

    LaunchedEffect(Unit) {
        viewModel.navigateToHome.collect {
            navigateToHome()
        }
    }

    LaunchedEffect(true) {
        viewModel.error.collect {
            isSnackBarMessageError = true
            shackBarHostState.showSnackbar(it)
        }
    }

    LaunchedEffect(true) {
        viewModel.success.collect {
            isSnackBarMessageError = false
            shackBarHostState.showSnackbar(it)
        }
    }

    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.link.collect {
            val customIntent = CustomTabsIntent.Builder()
                .setDefaultColorSchemeParams(
                    CustomTabColorSchemeParams.Builder()
                        .setToolbarColor(0x006B5D)
                        .build()
                )
                .setColorSchemeParams(
                    CustomTabsIntent.COLOR_SCHEME_DARK,
                    CustomTabColorSchemeParams.Builder()
                        .setToolbarColor(0x006B5D)
                        .build()
                )
            openCustomTab(
                context,
                customIntent.build(),
                Uri.parse(it)
            )
        }
    }

    SignInScreenContent(
        loading = viewModel.loading.collectAsState().value,
        shackBarHostState = shackBarHostState,
        isSnackBarMessageError = isSnackBarMessageError,

        onResetPasswordClick = remember { { viewModel.onResetPasswordClick() } },
        onSignInClick = remember { { viewModel.onSignInClick() } },
        onSignInWithGoogleClick = remember { { viewModel.onSignInWithGoogleClick(launcher) } },
    )
}

fun openCustomTab(context: Context, customTabsIntent: CustomTabsIntent, uri: Uri?) {
    customTabsIntent.launchUrl(context, uri!!)
}

@Composable
private fun SignInScreenContent(
    loading: Boolean = false,
    shackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    isSnackBarMessageError: Boolean? = null,

    onResetPasswordClick: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    onSignInWithGoogleClick: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier
            .imePadding()
            .animateContentSize(),
        snackbarHost = {
            SnackbarHost(hostState = shackBarHostState, snackbar = {
                if (isSnackBarMessageError == false) SnackbarSuccess(it)
                else SnackbarError(it)
            })
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(0.5f))

            Image(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
//            TextButton(onClick = onResetPasswordClick) {
//                Text(text = "Сбросить пароль")
//            }

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
                Text(text = "Войти по Trust Bank ID")
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "или")
                Spacer(modifier = Modifier.width(16.dp))
                HorizontalDivider(Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                onClick = onSignInWithGoogleClick
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Продолжить через Google")
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
            SignInScreenContent()
        }
    }
}