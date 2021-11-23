package org.nextchat.nextchat.data.responses.auth

data class SignUpResponse(
    val access_token: String,
    val id: String,
    val permissions: List<Any>,
    val recovery_codes: List<String>,
    val role: String,
    val username: String
)
