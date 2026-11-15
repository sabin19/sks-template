package com.sks.template.core.domain.di

import com.sks.template.core.domain.onboarding.GetOnboardingItemsUseCase
import com.sks.template.core.domain.onboarding.SetOnboardingCompletedUseCase
import org.koin.dsl.module

val provideDomainModule = module {
    factory { GetOnboardingItemsUseCase(get(), get()) }
    factory { SetOnboardingCompletedUseCase(get(), get()) }
}
