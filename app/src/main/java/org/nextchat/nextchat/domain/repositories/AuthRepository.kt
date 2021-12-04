package org.nextchat.nextchat.domain.repositories

import android.util.Log
import org.nextchat.nextchat.data.datastore.SessionDataStore
import org.nextchat.nextchat.data.network.services.AuthService
import org.nextchat.nextchat.domain.models.auth.SignInBody
import org.nextchat.nextchat.utils.*
import java.lang.Exception

class AuthRepository(
    private val authService: AuthService,
    private val sessionDataStore: SessionDataStore
) {
    suspend fun isLogged(): Boolean {
        val accountToken = sessionDataStore.getToken()
        Log.i("Account_token", "Token: $accountToken")
        return accountToken != null
    }

    suspend fun signIn(
        username: String,
        password: String
    ): ApiResult<ApiError, Boolean> {
        return try {
            val result = authService.signIn(SignInBody(password, username))
            sessionDataStore.setDataFromSignIn(result)
            successResult(true)
        } catch (e: Exception) {
            errorResult(parseApiErrors(e))
        }
    }
}
