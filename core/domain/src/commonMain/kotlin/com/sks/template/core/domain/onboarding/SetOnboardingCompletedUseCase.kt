package com.sks.template.core.domain.onboarding

import com.sks.template.core.common.dispatcher.DispatcherProvider
import com.sks.template.core.data.repository.OnboardingRepository
import com.sks.template.core.domain.UseCase

class SetOnboardingCompletedUseCase(
    private val repository: OnboardingRepository,
    dispatchers: DispatcherProvider
) : UseCase<Boolean, Unit>(dispatchers.io) {
    override suspend fun execute(parameters: Boolean) {
        repository.setOnboardingCompleted(parameters)
    }
}
