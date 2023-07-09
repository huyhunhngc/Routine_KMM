package com.dotsdev.routine.domain.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingDataStore(private val dataStore: DataStore<Preferences>) {

    suspend fun set(key: String, value: String) {
        val stringKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[stringKey] = value
        }
    }

    suspend fun set(key: String, value: Long) {
        val longKey = longPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[longKey] = value
        }
    }

    suspend fun set(key: String, value: Int) {
        val intKey = intPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[intKey] = value
        }
    }

    fun getString(key: String): Flow<String?> =
        dataStore.data.map { preferences ->
            val stringKey = stringPreferencesKey(key)
            preferences[stringKey]
        }

    fun getStringOrDefault(key: String, default: String): Flow<String> =
        dataStore.data.map { preferences ->
            val stringKey = stringPreferencesKey(key)
            preferences[stringKey] ?: default
        }

    fun getLong(key: String): Flow<Long?> =
        dataStore.data.map { preferences ->
            val longKey = longPreferencesKey(key)
            preferences[longKey]
        }

    fun getLongOrDefault(key: String, default: Long): Flow<Long> =
        dataStore.data.map { preferences ->
            val longKey = longPreferencesKey(key)
            preferences[longKey] ?: default
        }

    fun getInt(key: String): Flow<Int?> =
        dataStore.data.map { preferences ->
            val intKey = intPreferencesKey(key)
            preferences[intKey]
        }

    fun getIntOrDefault(key: String, default: Int): Flow<Int> =
        dataStore.data.map { preferences ->
            val intKey = intPreferencesKey(key)
            preferences[intKey] ?: default
        }
}