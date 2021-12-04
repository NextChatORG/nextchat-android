package org.nextchat.nextchat.domain.models.auth

data class SignInBody(
    val password: String,
    val username: String
)
