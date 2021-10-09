package org.nextchat.nextchat.repositories

import androidx.navigation.NavController
import com.apollographql.apollo3.ApolloClient
import org.nextchat.nextchat.constants.AccountStorage
import org.nextchat.nextchat.constants.Url
import org.nextchat.nextchat.repositories.index.SignUpRepository

class RepositoriesManager(
    internal val accountStorage: AccountStorage,
    internal val navController: NavController,
) {
    internal val apolloClient = ApolloClient(Url.API_URL)

    // Index repositories
    val signUp: SignUpRepository = SignUpRepository(this)
}
