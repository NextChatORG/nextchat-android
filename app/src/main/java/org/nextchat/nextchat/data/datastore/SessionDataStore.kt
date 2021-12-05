package org.nextchat.nextchat.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.nextchat.nextchat.domain.models.auth.SignInResponse
import org.nextchat.nextchat.domain.models.datastore.SessionData

class SessionDataStore(
    private val context: Context
) {
    companion object {
        private val permissionsKey = stringPreferencesKey("account_permissions")
        private val roleKey = stringPreferencesKey("account_role")
        private val tokenKey = stringPreferencesKey("account_token")
        private val userIdKey = stringPreferencesKey("account_id")
        private val usernameKey = stringPreferencesKey("account_username")
    }

    suspend fun clearData() {
        context.sessionDataStore.edit { preferences ->
            preferences.remove(permissionsKey)
            preferences.remove(roleKey)
            preferences.remove(tokenKey)
            preferences.remove(userIdKey)
            preferences.remove(usernameKey)
        }
    }

    suspend fun getData(): SessionData? {
        return context.sessionDataStore.data.map { preferences ->
            val permissions = preferences[permissionsKey]
            val roleName = preferences[roleKey]
            val userId = preferences[userIdKey]
            val username = preferences[usernameKey]

            return@map if (permissions != null && roleName != null && userId != null && username != null) {
                SessionData(permissions.split(';'), roleName, userId, username)
            } else {
                null
            }
        }.first()
    }

    suspend fun getToken(): String? {
        return context.sessionDataStore.data.map { preferences ->
            return@map preferences[tokenKey]
        }.first()
    }

    suspend fun setDataFromSignIn(data: SignInResponse) {
        context.sessionDataStore.edit { preferences ->
            preferences[permissionsKey] = data.permissions.joinToString(";")
            preferences[roleKey] = data.role
            preferences[tokenKey] = data.access_token
            preferences[userIdKey] = data.id
            preferences[usernameKey] = data.username
        }
    }
}

private val Context.sessionDataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
