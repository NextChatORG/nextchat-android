package org.nextchat.nextchat.services

import org.nextchat.nextchat.constants.Url.AUTH_BASE_URL
import org.nextchat.nextchat.data.requests.auth.SignInBody
import org.nextchat.nextchat.data.requests.auth.SignUpBody
import org.nextchat.nextchat.data.responses.auth.SignInResponse
import org.nextchat.nextchat.data.responses.auth.SignUpResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("signin")
    suspend fun signIn(@Body() data: SignInBody): SignInResponse

    @POST("signup")
    suspend fun signUp(@Body() data: SignUpBody): SignUpResponse

    companion object {
        var service: AuthService? = null

        fun getInstance() : AuthService {
            if (service == null) {
                service = Retrofit
                    .Builder()
                    .baseUrl(AUTH_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AuthService::class.java)
            }

            return service!!
        }
    }
}
