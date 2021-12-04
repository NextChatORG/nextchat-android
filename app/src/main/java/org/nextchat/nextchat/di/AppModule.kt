package org.nextchat.nextchat.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.nextchat.nextchat.data.datastore.SessionDataStore
import org.nextchat.nextchat.data.network.client.NextChatAPIClient
import org.nextchat.nextchat.data.network.services.AuthService
import org.nextchat.nextchat.domain.repositories.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Data Stores
    @Provides
    @Singleton
    fun sessionDataStore(
        @ApplicationContext context: Context
    ): SessionDataStore = SessionDataStore(context)

    // HTTP Clients
    @Provides
    fun authorizedClient(
        token: String
    ): OkHttpClient = NextChatAPIClient.createAuthorizedClient(token)

    // Repositories
    @Provides
    @Singleton
    fun authRepository(
        authService: AuthService,
        sessionDataStore: SessionDataStore
    ): AuthRepository = AuthRepository(authService, sessionDataStore)

    // Services
    @Provides
    @Singleton
    fun authService(): AuthService = NextChatAPIClient.createAuthService()
}
