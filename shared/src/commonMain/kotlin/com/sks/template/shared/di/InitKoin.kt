package com.sks.template.shared.di

import com.sks.template.core.common.di.provideCommonModule
import com.sks.template.core.data.di.provideDataModule
import com.sks.template.core.datastore.di.provideDatastoreModule
import com.sks.template.core.domain.di.provideDomainModule
import com.sks.template.core.network.di.provideNetworkModule
import com.sks.template.core.permission.di.providePermissionModule
import com.sks.template.feature.onboarding.di.onboardingModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun initKoin(appModules: List<Module> = emptyList(), config: KoinAppDeclaration? = null) {
    val koinApplication = koinApplication {
        allowOverride(true)
        config?.invoke(this)
        modules(
            listOf(
                module { includes(appModules) },
                provideCommonModule,
                provideDatastoreModule,
                provideNetworkModule,
                provideDataModule,
                provideDomainModule,
                providePermissionModule,
                onboardingModule
            )
        )
        createEagerInstances()
    }
    startKoin(koinApplication)
}
