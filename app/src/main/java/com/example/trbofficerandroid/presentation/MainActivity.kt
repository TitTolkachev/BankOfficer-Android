package com.example.trbofficerandroid.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.trbofficerandroid.domain.model.AppTheme
import com.example.trbofficerandroid.presentation.navigation.RootNavGraph
import com.example.trbofficerandroid.presentation.navigation.Screen
import com.example.trbofficerandroid.presentation.theme.AppTheme
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by inject(MainViewModel::class.java)
    private lateinit var splashScreen: SplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        // Инициализация SplashScreen
        splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    val startDestination = viewModel.startDestination.value
                    return if (startDestination != null) {
                        initCompose(startDestination = startDestination)
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            },
        )
    }

    private fun initCompose(startDestination: Screen) {
        setContent {
            val theme by viewModel.appTheme.collectAsState(null)
            if (theme != null) {
                CompositionLocalProvider(
                    LocalConfiguration provides LocalConfiguration.current.apply {
                        uiMode = when (theme) {
                            AppTheme.DARK -> Configuration.UI_MODE_NIGHT_YES
                            AppTheme.LIGHT -> Configuration.UI_MODE_NIGHT_NO
                            else -> Configuration.UI_MODE_NIGHT_NO
                        }
                    }
                ) {
                    AppTheme(darkTheme = isSystemInDarkTheme()) {
                        Surface(
                            color = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.onBackground,
                        ) {
                            RootNavGraph(
                                navController = rememberNavController(),
                                startDestination = startDestination.route,
                                modifier = Modifier,
                            )
                        }
                    }
                }
            }
        }
    }
}