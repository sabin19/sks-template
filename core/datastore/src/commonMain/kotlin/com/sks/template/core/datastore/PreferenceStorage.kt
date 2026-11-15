package com.sks.template.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sks.template.core.model.theme.ThemeConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PreferenceStorage {
    val isOnboardingCompleted: Flow<Boolean>
    suspend fun setOnboardingCompleted(completed: Boolean)

    val token: Flow<String?>
    suspend fun setToken(token: String?)

    val themeConfig: Flow<ThemeConfig>
    suspend fun setThemeConfig(themeConfig: ThemeConfig)

    suspend fun clearAll()
}

class DefaultPreferenceStorage(
    private val dataStore: DataStore<Preferences>
) : PreferenceStorage {
    private object PreferencesKeys {
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
        val THEME_CONFIG = stringPreferencesKey("theme_config")
    }

    override val isOnboardingCompleted: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.ONBOARDING_COMPLETED] ?: false
    }

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ONBOARDING_COMPLETED] = completed
        }
    }

    override val token: Flow<String?> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.AUTH_TOKEN]
    }

    override suspend fun setToken(token: String?) {
        dataStore.edit { preferences ->
            if (token != null) {
                preferences[PreferencesKeys.AUTH_TOKEN] = token
            } else {
                preferences.remove(PreferencesKeys.AUTH_TOKEN)
            }
        }
    }

    override val themeConfig: Flow<ThemeConfig> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.THEME_CONFIG]?.let {
            runCatching { ThemeConfig.valueOf(it) }.getOrDefault(ThemeConfig.FOLLOW_SYSTEM)
        } ?: ThemeConfig.FOLLOW_SYSTEM
    }

    override suspend fun setThemeConfig(themeConfig: ThemeConfig) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME_CONFIG] = themeConfig.name
        }
    }

    override suspend fun clearAll() {
        dataStore.edit { it.clear() }
    }
}
