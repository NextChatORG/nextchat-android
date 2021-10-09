package org.nextchat.nextchat.utils

import androidx.datastore.preferences.core.stringPreferencesKey

object storage_keys {
    val accountId = stringPreferencesKey("account_id")
    val accountPermissions = stringPreferencesKey("account_permissions")
    val accountRole = stringPreferencesKey("account_role")
    val accountToken = stringPreferencesKey("account_token")
    val accountUsername = stringPreferencesKey("account_username")
}
