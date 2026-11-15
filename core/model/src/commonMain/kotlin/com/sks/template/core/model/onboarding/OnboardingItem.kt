package com.sks.template.core.model.onboarding

import kotlinx.serialization.Serializable

@Serializable
data class OnboardingItem(
    val id: String,
    val title: String,
    val description: String,
    val iconUrl: String? = null
)
