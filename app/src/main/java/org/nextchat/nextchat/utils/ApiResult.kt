package org.nextchat.nextchat.utils

sealed class ApiResult<out E, out R> {
    data class Error<out E>(val error: E) : ApiResult<E, Nothing>()
    data class Success<out R>(val data: R) : ApiResult<Nothing, R>()

    fun isError(): Boolean = this is Error
    fun isSuccess(): Boolean = this is Success

    fun getErrorOrNull(): E? {
        return if (this is Error) {
            error
        } else {
            null
        }
    }

    fun getOrNull(): R? {
        return if (this is Success) {
            data
        } else {
            null
        }
    }
}

fun<T> errorResult(error: T) = ApiResult.Error(error)
fun<T> successResult(data: T) = ApiResult.Success(data)
