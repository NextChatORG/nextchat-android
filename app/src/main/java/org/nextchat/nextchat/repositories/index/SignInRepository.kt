package org.nextchat.nextchat.repositories.index

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.apollographql.apollo3.exception.ApolloException
import org.nextchat.nextchat.SignInQuery
import org.nextchat.nextchat.constants.StorageKeys
import org.nextchat.nextchat.repositories.RepositoriesManager
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.utils.getErrorTextByKey

class SignInRepository(
    private val manager: RepositoriesManager,
) {
    suspend fun handleSubmit(
        // Fields
        username: String,
        password: String,
        // General
        context: Context,
        // Callbacks
        onErrors: (Map<String, String>) -> Unit
    ) {
        try {
            val response = this.manager.apolloClient.query(SignInQuery(
                username = username,
                password = password,
            ))

            if (response.hasErrors()) {
                val errors = mutableMapOf<String, String>()

                for (error in response.errors!!) {
                    if (error.extensions != null) {
                        val formField: String? = error.extensions!!["form_field"] as String?
                        val textKey: String? = error.extensions!!["text_key"] as String?

                        if (formField != null && textKey != null) {
                            errors[formField] = getErrorTextByKey(context, textKey)
                        } else {
                            errors["general"] = error.message
                        }
                    } else {
                        errors["general"] = error.message
                    }
                }

                onErrors(errors)
            } else if (response.data != null) {
                // Clear all errors messages.
                onErrors(mutableMapOf())

                val data = response.data!!.signIn

                // Save data
                this.manager.accountStorage.edit { preferences ->
                    preferences[StorageKeys.accountId] = data.id as String
                    preferences[StorageKeys.accountPermissions] = data.permissions.joinToString(separator = ";")
                    preferences[StorageKeys.accountRole] = data.role
                    preferences[StorageKeys.accountToken] = data.accessToken
                    preferences[StorageKeys.accountUsername] = data.username
                }

                // Redirect to splash screen.
                this.manager.navController.navigate(route = Screens.Splash.route) {
                    popUpTo(route = Screens.Splash.route)
                }
            }
        } catch (e: ApolloException) {
            println("Sign in error: $e")
        }
    }
}
