package com.sks.template.core.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.WebLocalStorage
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesSerializer
import com.sks.template.core.datastore.dataStoreFileName
import org.koin.dsl.module

actual val platformDatastoreModule = module {
    single<DataStore<Preferences>> {
        DataStoreFactory.create(
            storage = WebLocalStorage(
                serializer = PreferencesSerializer,
                name = dataStoreFileName
            )
        )
    }
}
