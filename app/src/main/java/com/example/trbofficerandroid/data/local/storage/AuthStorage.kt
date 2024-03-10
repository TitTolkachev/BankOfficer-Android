package com.example.trbofficerandroid.data.local.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AuthStorage(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    suspend fun getUserId() = context.dataStore.data.first()[USER_ID]?.ifBlank { null }

    suspend fun updateUserId(id: String?) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = id ?: ""
        }
    }

    companion object {
        private val USER_ID = stringPreferencesKey("userId")
    }
}