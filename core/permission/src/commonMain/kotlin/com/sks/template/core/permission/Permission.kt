package com.sks.template.core.permission

enum class Permission {
    CAMERA,
    LOCATION,
    NOTIFICATIONS
}

enum class PermissionState {
    GRANTED,
    DENIED,
    NOT_DETERMINED
}

interface PermissionsService {
    suspend fun checkPermission(permission: Permission): PermissionState
    suspend fun requestPermission(permission: Permission): PermissionState
}

class DefaultPermissionsService : PermissionsService {
    override suspend fun checkPermission(permission: Permission): PermissionState {
        return PermissionState.GRANTED
    }

    override suspend fun requestPermission(permission: Permission): PermissionState {
        return PermissionState.GRANTED
    }
}
