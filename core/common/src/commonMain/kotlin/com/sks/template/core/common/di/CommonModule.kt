package com.sks.template.core.common.di

import com.sks.template.core.common.dispatcher.DefaultDispatcherProvider
import com.sks.template.core.common.dispatcher.DispatcherProvider
import org.koin.dsl.module

val provideCommonModule = module {
    single<DispatcherProvider> { DefaultDispatcherProvider() }
}
