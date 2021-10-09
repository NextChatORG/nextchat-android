package org.nextchat.nextchat.repositories.index

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavController
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.nextchat.nextchat.SignUpMutation
import org.nextchat.nextchat.core.Screens
import org.nextchat.nextchat.core.apolloClient
import org.nextchat.nextchat.utils.getTextByKey
import org.nextchat.nextchat.utils.storage_keys

class SignUpRepository {
    fun handleSubmit(
        // Fields
        username: String,
        password: String,
        confirmPassword: String,
        // General
        accountStorage: DataStore<Preferences>,
        context: Context,
        navController: NavController,
        scope: CoroutineScope,
        // Callbacks
        onErrors: (Map<String, String>) -> Unit
    ) {
        scope.launch {
            try {
                val response = apolloClient.mutate(
                    SignUpMutation(
                        username = username,
                        password = password,
                        confirmPassword = confirmPassword
                    )
                )

                if (response.hasErrors()) {
                    val errors = mutableMapOf<String, String>()

                    for (error in response.errors!!) {
                        if (error.extensions != null) {
                            val formField: String? = error.extensions!!["form_field"] as String?
                            val textKey: String? = error.extensions!!["text_key"] as String?

                            if (formField != null && textKey != null) {
                                errors[formField] = getTextByKey(context, textKey)
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
                    accountStorage.edit { preferences ->
                        preferences[storage_keys.accountId] = data.id as String
                        preferences[storage_keys.accountPermissions] = data.permissions.joinToString(separator = ";")
                        preferences[storage_keys.accountRole] = data.role
                        preferences[storage_keys.accountToken] = data.accessToken
                        preferences[storage_keys.accountUsername] = data.username
                    }

                    // Redirect to recovery codes screen.
                    navController.navigate(
                        route = Screens.RecoveryCodesScreen.withArguments(
                            data.recoverCodes.joinToString(
                                separator = ";"
                            )
                        )
                    ) {
                        popUpTo(route = Screens.HomeScreen.route) { inclusive = true }
                    }
                }
            } catch (e: ApolloException) {
                println("Error: $e")
            }
        }
    }
}
