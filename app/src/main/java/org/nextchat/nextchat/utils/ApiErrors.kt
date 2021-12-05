package org.nextchat.nextchat.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.nextchat.nextchat.R
import org.nextchat.nextchat.domain.models.errors.FieldErrorResponse
import retrofit2.HttpException
import java.lang.Exception

private const val TAG = "API_ERRORS"

data class ApiError(
    var fieldError: FieldErrorResponse?,
    var otherError: Exception?
)

fun getErrorTextByKey(context: Context, textKey: String): String {
    return when (textKey) {
        // General messages
        "INTERNAL_ERROR" -> context.getString(R.string.INTERNAL_ERROR)

        // Auth messages
        "AUTH_EMPTY_USERNAME" -> context.getString(R.string.AUTH_EMPTY_USERNAME)
        "AUTH_USERNAME_INVALID_FORMAT" -> context.getString(R.string.AUTH_USERNAME_INVALID_FORMAT)
        "AUTH_USERNAME_INVALID_LENGTH" -> context.getString(R.string.AUTH_USERNAME_INVALID_LENGTH)
        "AUTH_DUPLICATED_USERNAME" -> context.getString(R.string.AUTH_DUPLICATED_USERNAME)

        "AUTH_EMPTY_PASSWORD" -> context.getString(R.string.AUTH_EMPTY_PASSWORD)
        "AUTH_PASSWORD_INVALID_FORMAT" -> context.getString(R.string.AUTH_PASSWORD_INVALID_FORMAT)
        "AUTH_PASSWORD_INVALID_LENGTH" -> context.getString(R.string.AUTH_PASSWORD_INVALID_LENGTH)

        "AUTH_EMPTY_REPEAT_PASSWORD" -> context.getString(R.string.AUTH_EMPTY_REPEAT_PASSWORD)
        "AUTH_PASSWORDS_DONT_MATCH" -> context.getString(R.string.AUTH_PASSWORDS_DONT_MATCH)

        "AUTH_WRONG_PASSWORD" -> context.getString(R.string.AUTH_WRONG_PASSWORD)

        // Beta messages
        "BETA_EMPTY_KEY" -> context.getString(R.string.BETA_EMPTY_KEY)
        "BETA_INVALID_KEY" -> context.getString(R.string.BETA_INVALID_KEY)

        // Default
        else -> textKey
    }
}

fun parseApiErrors(e: Exception) : ApiError {
    val result = ApiError(
        fieldError = null,
        otherError = null
    )

    if (e is HttpException) {
        val body = e.response()!!.errorBody()!!.string()

        Log.i(TAG, "Body: $body")

        try {
            result.fieldError = Gson().fromJson(
                body,
                object : TypeToken<FieldErrorResponse>() {}.type
            )
        } catch (e: Exception) {
            Log.e(TAG, "Cannot parse field error: $e")
        }
    } else {
        Log.e(TAG, "Unexpected error: $e")
        result.otherError = e
    }

    return result
}
