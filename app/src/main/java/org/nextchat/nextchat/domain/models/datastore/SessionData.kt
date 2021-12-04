package org.nextchat.nextchat.domain.models.datastore

data class SessionData(
    val permissions: List<String>,
    val roleName: String,
    val userId: String,
    val username: String
)
