package org.nextchat.nextchat.data.responses

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.nextchat.nextchat.data.responses.error.FieldErrorResponse
import retrofit2.HttpException
import java.lang.Exception

data class ApiError(
    var fieldError: FieldErrorResponse?
)

fun parseApiErrors(e: Exception) : ApiError {
    val result = ApiError(fieldError = null)

    if (e is HttpException) {
        val body = e.response()!!.errorBody()!!.string()

        result.fieldError = Gson().fromJson(
            body,
            object : TypeToken<FieldErrorResponse>() {}.type
        )
    }

    return result
}
