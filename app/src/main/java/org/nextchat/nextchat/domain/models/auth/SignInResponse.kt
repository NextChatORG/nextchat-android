package org.nextchat.nextchat.domain.models.auth

data class SignInResponse(
    val access_token: String,
    val id: String,
    val permissions: List<String>,
    val role: String,
    val username: String
)
