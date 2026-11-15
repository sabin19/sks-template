package com.sks.template.shared.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sks.template.core.datastore.PreferenceStorage
import com.sks.template.core.designsystem.theme.AppTheme
import com.sks.template.core.model.theme.ThemeConfig
import com.sks.template.feature.onboarding.navigation.OnboardingNavigation
import com.sks.template.feature.onboarding.navigation.onboardingScreen
import org.koin.compose.koinInject

@Composable
fun App() {
    val preferenceStorage = koinInject<PreferenceStorage>()
    val themeConfig by preferenceStorage.themeConfig.collectAsState(initial = ThemeConfig.FOLLOW_SYSTEM)

    AppTheme(themeConfig = themeConfig) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = OnboardingNavigation,
            modifier = Modifier.fillMaxSize()
        ) {
            onboardingScreen(
                onFinishOnboarding = {
                    // Navigate to next screen after onboarding completes
                }
            )
        }
    }
}
