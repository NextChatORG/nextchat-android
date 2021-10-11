package org.nextchat.nextchat.ui.screens.index

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.nextchat.nextchat.ui.layouts.IndexLayout
import org.nextchat.nextchat.R
import org.nextchat.nextchat.repositories.index.SignInRepository
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.ui.widgets.FractionSpacer
import org.nextchat.nextchat.ui.widgets.PasswordInput
import org.nextchat.nextchat.ui.widgets.ResourceText
import org.nextchat.nextchat.ui.widgets.TextFieldError

@Composable
fun SignInScreen(
    navController: NavController,
    signInRepository: SignInRepository,
) {
    // States
    var username by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    // Content
    IndexLayout(title = R.string.sign_in_screen_title) {
        // Username input
        OutlinedTextField(
            isError = usernameError.isNotEmpty(),
            label = { ResourceText(id = R.string.sign_up_screen_username_input) },
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = {
                username = it
                usernameError = ""
            }
        )
        TextFieldError(message = usernameError)
        FractionSpacer(fraction = 0.5F)
        // Password input
        PasswordInput(
            error = passwordError,
            label = R.string.sign_up_screen_password_input,
            value = password,
            onValueChange = { value ->
                password = value
                passwordError = ""
            }
        )
        // Terms of conditions and privacy policy message
        FractionSpacer(fraction = 3F)
        // Actions
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            SignInButton(
                // Fields
                username = username,
                password = password,
                // General
                signInRepository = signInRepository,
                // Callbacks
                onErrors = { errors ->
                    // Reset errors
                    usernameError = ""
                    passwordError = ""
                    password = ""

                    // Set new errors
                    for ((field, message) in errors.iterator()) {
                        when (field) {
                            "username" -> { usernameError = message }
                            "password" -> { passwordError = message }
                            else -> { println("Cannot parse: $field as error (Message: $message)") }
                        }
                    }
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            ForgotPasswordButton(navController)
        }
    }
}

@Composable
private fun ForgotPasswordButton(navController: NavController) {
    TextButton(
        onClick = {
            navController.navigate(route = Screens.ForgotPassword.route)
        }
    ) {
        ResourceText(
            id = R.string.sign_in_screen_forgot_password_button,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SignInButton(
    // Fields
    username: String,
    password: String,
    // General
    signInRepository: SignInRepository,
    // Callbacks
    onErrors: (Map<String, String>) -> Unit,
) {
    // Hooks
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Content
    Button(onClick = {
        scope.launch {
            signInRepository.handleSubmit(
                // Fields
                username = username,
                password = password,
                // General
                context = context,
                // Callbacks
                onErrors = onErrors,
            )
        }
    }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, "")
            FractionSpacer(fraction = 0.5F)
            ResourceText(id = R.string.sign_in_screen_submit_button)
        }
    }
}
