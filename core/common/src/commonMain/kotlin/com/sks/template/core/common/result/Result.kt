package com.sks.template.core.common.result

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    sealed class Error(val exception: Throwable) : Result<Nothing>() {
        class RecoverableError(exception: Throwable) : Error(exception)
        class NonRecoverableError(exception: Throwable) : Error(exception)
    }
    data object Loading : Result<Nothing>()

    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error
    val isLoading: Boolean get() = this is Loading

    fun getOrNull(): T? = (this as? Success)?.data
}
