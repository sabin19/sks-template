package com.sks.template.core.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sks.template.core.datastore.createDataStore
import com.sks.template.core.datastore.dataStoreFileName
import org.koin.dsl.module
import java.io.File

actual val platformDatastoreModule = module {
    single<DataStore<Preferences>> {
        createDataStore {
            val appDir = File(System.getProperty("user.home"), ".sks-template")
            if (!appDir.exists()) {
                appDir.mkdirs()
            }
            File(appDir, dataStoreFileName).absolutePath
        }
    }
}
