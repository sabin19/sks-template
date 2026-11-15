package com.sks.template.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sks.template.feature.onboarding.ui.OnboardingRoute
import kotlinx.serialization.Serializable

@Serializable
data object OnboardingNavigation

fun NavController.navigateToOnboarding(navOptions: NavOptions? = null) {
    navigate(route = OnboardingNavigation, navOptions = navOptions)
}

fun NavGraphBuilder.onboardingScreen(
    onFinishOnboarding: () -> Unit
) {
    composable<OnboardingNavigation> {
        OnboardingRoute(onFinishOnboarding = onFinishOnboarding)
    }
}
