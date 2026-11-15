package com.sks.template.core.network.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js
import org.koin.dsl.module

actual val platformNetworkModule = module {
    single<HttpClientEngine> {
        Js.create()
    }
}
