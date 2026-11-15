package com.sks.template.core.network.di

import com.sks.template.core.network.client.createHttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformNetworkModule: Module

val provideNetworkModule = module {
    includes(platformNetworkModule)
    single { createHttpClient(get<HttpClientEngine>(), get()) }
}
