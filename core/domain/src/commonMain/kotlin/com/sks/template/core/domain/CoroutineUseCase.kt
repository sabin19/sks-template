package com.sks.template.core.domain

import com.sks.template.core.common.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                Result.Success(execute(parameters))
            }
        } catch (e: Exception) {
            Result.Error.NonRecoverableError(e)
        }
    }

    protected abstract suspend fun execute(parameters: P): R
}
