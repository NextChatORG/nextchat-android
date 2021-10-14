package org.nextchat.nextchat.repositories.index

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.apollographql.apollo3.exception.ApolloException
import org.nextchat.nextchat.SignUpMutation
import org.nextchat.nextchat.constants.StorageKeys
import org.nextchat.nextchat.repositories.RepositoriesManager
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.utils.getErrorTextByKey

class SignUpRepository(
    private val manager: RepositoriesManager,
) {
    suspend fun handleSubmit(
        // Fields
        username: String,
        betaKey: String,
        password: String,
        repeatPassword: String,
        // General
        context: Context,
        // Callbacks
        onErrors: (Map<String, String>) -> Unit
    ) {
        try {
            val response = this.manager.apolloClient.mutate(SignUpMutation(
                username = username,
                betaKey = betaKey,
                password = password,
                repeatPassword = repeatPassword,
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

                val data = response.data!!.signUp

                // Save data
                this.manager.accountStorage.edit { preferences ->
                    preferences[StorageKeys.accountId] = data.id as String
                    preferences[StorageKeys.accountPermissions] = data.permissions.joinToString(separator = ";")
                    preferences[StorageKeys.accountRole] = data.role
                    preferences[StorageKeys.accountToken] = data.accessToken
                    preferences[StorageKeys.accountUsername] = data.username
                }

                // Redirect to recovery codes screen.
                this.manager.navController.navigate(
                    route = Screens.RecoveryCodes.withArguments(
                        data.recoveryCodes.joinToString(separator = ";")
                    )
                ) {
                    popUpTo(route = Screens.Home.route)
                }
            }
        } catch (e: ApolloException) {
            println("Sign up error: $e")
        }
    }
}
