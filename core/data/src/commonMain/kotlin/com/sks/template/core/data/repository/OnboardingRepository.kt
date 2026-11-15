package com.sks.template.core.data.repository

import com.sks.template.core.datastore.PreferenceStorage
import com.sks.template.core.model.onboarding.OnboardingItem
import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    fun getOnboardingItems(): List<OnboardingItem>
    val isOnboardingCompleted: Flow<Boolean>
    suspend fun setOnboardingCompleted(completed: Boolean)
}

class DefaultOnboardingRepository(
    private val preferenceStorage: PreferenceStorage
) : OnboardingRepository {

    override fun getOnboardingItems(): List<OnboardingItem> {
        return listOf(
            OnboardingItem(
                id = "1",
                title = "Welcome to KMP Template",
                description = "Clean Architecture with Kotlin Multiplatform, Compose Multiplatform, and modularized code."
            ),
            OnboardingItem(
                id = "2",
                title = "Modern Stack",
                description = "Powered by Koin, Ktor, Coil 3, DataStore, and Gradle convention plugins."
            ),
            OnboardingItem(
                id = "3",
                title = "Ready to Build",
                description = "Easily add new feature modules with one line of convention plugin application."
            )
        )
    }

    override val isOnboardingCompleted: Flow<Boolean> = preferenceStorage.isOnboardingCompleted

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        preferenceStorage.setOnboardingCompleted(completed)
    }
}
