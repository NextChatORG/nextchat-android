package org.nextchat.nextchat.data.requests.auth

data class SignInBody(
    val password: String,
    val username: String
)