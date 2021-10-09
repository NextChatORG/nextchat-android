package org.nextchat.nextchat.constants

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

object StorageKeys {
    val accountId = stringPreferencesKey("account_id")
    val accountPermissions = stringPreferencesKey("account_permissions")
    val accountRole = stringPreferencesKey("account_role")
    val accountToken = stringPreferencesKey("account_token")
    val accountUsername = stringPreferencesKey("account_username")
}

typealias AccountStorage = DataStore<Preferences>
