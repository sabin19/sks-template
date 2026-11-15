package com.sks.template.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sks.template.core.datastore.createDataStore
import org.koin.dsl.module

actual val platformDatastoreModule = module {
    single<DataStore<Preferences>> { createDataStore(get<Context>()) }
}
