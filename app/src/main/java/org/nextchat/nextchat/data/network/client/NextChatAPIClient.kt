package org.nextchat.nextchat.data.network.client

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.nextchat.nextchat.data.network.constant.Url.AUTH_BASE_URL
import org.nextchat.nextchat.data.network.services.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NextChatAPIClient {
    fun createAuthorizedClient(token: String): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            val request = chain
                .request()
                .newBuilder()
                .addHeader("Authorization", token)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val client = OkHttpClient
            .Builder()
            .addInterceptor(requestInterceptor)

        return client.build()
    }

    fun createAuthService(): AuthService {
        return Retrofit
            .Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }
}
