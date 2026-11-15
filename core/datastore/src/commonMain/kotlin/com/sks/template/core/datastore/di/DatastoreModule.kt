package com.sks.template.core.datastore.di

import com.sks.template.core.datastore.DefaultPreferenceStorage
import com.sks.template.core.datastore.PreferenceStorage
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformDatastoreModule: Module

val provideDatastoreModule = module {
    includes(platformDatastoreModule)
    single<PreferenceStorage> { DefaultPreferenceStorage(get()) }
}
