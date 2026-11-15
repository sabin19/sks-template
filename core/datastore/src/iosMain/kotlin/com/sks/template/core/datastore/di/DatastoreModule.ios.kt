package com.sks.template.core.datastore.di

import com.sks.template.core.datastore.createDataStore
import org.koin.dsl.module

actual val platformDatastoreModule = module {
    single { createDataStore() }
}
