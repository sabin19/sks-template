package com.sks.template.core.data.di

import com.sks.template.core.data.repository.DefaultOnboardingRepository
import com.sks.template.core.data.repository.OnboardingRepository
import org.koin.dsl.module

val provideDataModule = module {
    single<OnboardingRepository> { DefaultOnboardingRepository(get()) }
}
