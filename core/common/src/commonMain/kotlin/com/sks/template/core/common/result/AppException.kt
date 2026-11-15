package com.sks.template.core.common.result

open class AppException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception(message, cause)

class NetworkException(
    val statusCode: Int? = null,
    message: String? = null,
    cause: Throwable? = null
) : AppException(message, cause)
