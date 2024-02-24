package com.example.trbofficerandroid.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.trbofficerandroid.presentation.navigation.RootNavGraph
import com.example.trbofficerandroid.presentation.navigation.Screen
import com.example.trbofficerandroid.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                ) {
                    RootNavGraph(
                        navController = rememberNavController(),
                        startDestination = Screen.Home.route,
                        modifier = Modifier,
                    )
                }
            }
        }
    }
}