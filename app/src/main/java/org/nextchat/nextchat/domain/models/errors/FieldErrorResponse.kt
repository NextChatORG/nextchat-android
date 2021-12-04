package org.nextchat.nextchat.domain.models.errors

data class FieldErrorResponse(
    val description: String,
    val `field`: String,
    val key: String
)
