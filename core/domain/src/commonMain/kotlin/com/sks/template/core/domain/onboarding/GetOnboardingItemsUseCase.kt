package com.sks.template.core.domain.onboarding

import com.sks.template.core.common.dispatcher.DispatcherProvider
import com.sks.template.core.data.repository.OnboardingRepository
import com.sks.template.core.domain.UseCase
import com.sks.template.core.model.onboarding.OnboardingItem

class GetOnboardingItemsUseCase(
    private val repository: OnboardingRepository,
    dispatchers: DispatcherProvider
) : UseCase<Unit, List<OnboardingItem>>(dispatchers.default) {
    override suspend fun execute(parameters: Unit): List<OnboardingItem> {
        return repository.getOnboardingItems()
    }
}
