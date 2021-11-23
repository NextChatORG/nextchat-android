package org.nextchat.nextchat.models.index

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.nextchat.nextchat.constants.AccountStorage
import org.nextchat.nextchat.constants.StorageKeys
import org.nextchat.nextchat.data.requests.auth.SignInBody
import org.nextchat.nextchat.data.responses.parseApiErrors
import org.nextchat.nextchat.services.AuthService
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.utils.getErrorTextByKey
import java.lang.Exception

class SignInViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)

    var username by mutableStateOf("")
    var usernameError by mutableStateOf("")

    var password by mutableStateOf("")
    var passwordError by mutableStateOf("")

    fun handleChangeUsername(value: String) {
        username = value
        usernameError = ""
    }

    fun handleChangePassword(value: String) {
        password = value
        passwordError = ""
    }

    fun submit(
        accountStorage: AccountStorage,
        navController: NavController,
        context: Context
    ) {
        if (username.isEmpty()) {
            usernameError = getErrorTextByKey(context, "AUTH_EMPTY_USERNAME")
            return
        }

        if (password.isEmpty()) {
            passwordError = getErrorTextByKey(context, "AUTH_EMPTY_PASSWORD")
            return
        }

        viewModelScope.launch {
            isLoading = true

            try {
                val response = AuthService.getInstance().signIn(SignInBody(
                    password = password,
                    username = username
                ))

                usernameError = ""
                passwordError = ""

                // Save data
                accountStorage.edit { preferences ->
                    preferences[StorageKeys.accountId] = response.id
                    preferences[StorageKeys.accountPermissions] = response.permissions.joinToString(separator = ";")
                    preferences[StorageKeys.accountRole] = response.role
                    preferences[StorageKeys.accountToken] = response.access_token
                    preferences[StorageKeys.accountUsername] = response.username
                }

                // Redirect to splash screen
                navController.navigate(route = Screens.Splash.route) {
                    popUpTo(route = Screens.Splash.route)
                }
            } catch (e: Exception) {
                val errors = parseApiErrors(e)
                if (errors.fieldError != null) {
                    val error = errors.fieldError!!

                    val errorMessage = getErrorTextByKey(context, error.key)
                    if (error.field == "username") {
                        usernameError = errorMessage
                    } else if (error.field == "password") {
                        passwordError = errorMessage
                    }
                }
            } finally {
                isLoading = false
            }
        }
    }
}
