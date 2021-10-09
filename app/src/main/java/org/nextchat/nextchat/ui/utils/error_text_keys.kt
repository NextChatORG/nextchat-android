package org.nextchat.nextchat.ui.utils

import android.content.Context
import org.nextchat.nextchat.R

fun getErrorTextByKey(context: Context, textKey: String): String {
    return when (textKey) {
        // General messages
        "INTERNAL_ERROR" -> context.getString(R.string.INTERNAL_ERROR)

        // Auth messages
        "AUTH_EMPTY_USERNAME" -> context.getString(R.string.AUTH_EMPTY_USERNAME)
        "AUTH_USERNAME_INVALID_LENGTH" -> context.getString(R.string.AUTH_USERNAME_INVALID_LENGTH)
        "AUTH_DUPLICATED_USERNAME" -> context.getString(R.string.AUTH_DUPLICATED_USERNAME)

        "AUTH_EMPTY_PASSWORD" -> context.getString(R.string.AUTH_EMPTY_PASSWORD)
        "AUTH_PASSWORD_INVALID_LENGTH" -> context.getString(R.string.AUTH_PASSWORD_INVALID_LENGTH)

        "AUTH_EMPTY_REPEAT_PASSWORD" -> context.getString(R.string.AUTH_EMPTY_REPEAT_PASSWORD)
        "AUTH_PASSWORDS_DONT_MATCH" -> context.getString(R.string.AUTH_PASSWORDS_DONT_MATCH)

        "AUTH_WRONG_PASSWORD" -> context.getString(R.string.AUTH_WRONG_PASSWORD)

        // Default
        else -> textKey
    }
}
