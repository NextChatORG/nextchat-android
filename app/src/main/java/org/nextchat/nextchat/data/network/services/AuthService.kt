package org.nextchat.nextchat.data.network.services

import org.nextchat.nextchat.domain.models.auth.SignInBody
import org.nextchat.nextchat.domain.models.auth.SignInResponse
import org.nextchat.nextchat.domain.models.auth.SignUpBody
import org.nextchat.nextchat.domain.models.auth.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("signin")
    suspend fun signIn(@Body data: SignInBody): SignInResponse

    @POST("signup")
    suspend fun signUp(@Body data: SignUpBody): SignUpResponse
}
