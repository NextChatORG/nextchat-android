package org.nextchat.nextchat.data.responses.error

data class FieldErrorResponse(
    val description: String,
    val `field`: String,
    val key: String
)
