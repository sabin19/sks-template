package com.sks.template.core.permission.di

import com.sks.template.core.permission.DefaultPermissionsService
import com.sks.template.core.permission.PermissionsService
import org.koin.dsl.module

val providePermissionModule = module {
    single<PermissionsService> { DefaultPermissionsService() }
}
