package org.nextchat.nextchat.domain.models.auth

data class SignUpBody(
    val beta_key: String,
    val password: String,
    val repeat_password: String,
    val username: String
)
