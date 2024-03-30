package com.example.trbofficerandroid.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.trbofficerandroid.domain.model.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PrefsDataStore(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "prefs")

    val appThemeFlow: Flow<AppTheme> = context.dataStore.data
        .map { preferences ->
            val value = preferences[APP_THEME] ?: AppTheme.UNSPECIFIED.name
            try {
                AppTheme.valueOf(value)
            } catch (_: Throwable) {
                AppTheme.UNSPECIFIED
            }
        }

    suspend fun updateAppTheme(newTheme: AppTheme) {
        if (newTheme == AppTheme.UNSPECIFIED) return
        context.dataStore.edit { settings ->
            settings[APP_THEME] = newTheme.name
        }
    }

    companion object {
        private val APP_THEME = stringPreferencesKey("app_theme")
    }
}