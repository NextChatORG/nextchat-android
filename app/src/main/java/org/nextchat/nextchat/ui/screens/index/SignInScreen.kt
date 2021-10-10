package org.nextchat.nextchat.ui.screens.index

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import org.nextchat.nextchat.ui.layouts.IndexLayout
import org.nextchat.nextchat.R
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.ui.widgets.FractionSpacer
import org.nextchat.nextchat.ui.widgets.ResourceText
import org.nextchat.nextchat.ui.widgets.TextFieldError

@Composable
fun SignInScreen(
    navController: NavController,
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
        // Repeat password input
        // Terms of conditions and privacy policy message
        FractionSpacer(fraction = 3F)
        // Actions
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            SignInButton(navController = navController)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            ForgotPasswordButton()
        }
    }
}

@Composable
private fun PasswordInput(
    error: String,
    label: Int,
    value: String,
    onValueChange: (String) -> Unit
) {
    // State
    var showingPassword by remember { mutableStateOf(false) }

    // Content
    OutlinedTextField(
        isError = error.isNotEmpty(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        label = { ResourceText(id = label) },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = { showingPassword = !showingPassword }) {
                Icon(
                    imageVector = if (showingPassword) { Icons.Filled.VisibilityOff } else { Icons.Filled.Visibility },
                    contentDescription = if (showingPassword) { "Hide password" } else { "Show password" }
                )
            }
        },
        value = value,
        visualTransformation = if (showingPassword) { VisualTransformation.None } else { PasswordVisualTransformation() },
        onValueChange = onValueChange
    )
    TextFieldError(message = error)
    FractionSpacer(fraction = 0.5F)
}

@Composable
private fun ForgotPasswordButton(
) {
    TextButton(
        onClick = {
            Toast.makeText(null,"Olvide contraseña", Toast.LENGTH_SHORT).show()
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
    navController: NavController
) {
    Button(onClick = {
        navController.navigate(route = Screens.Home.route)
    }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, "")
            FractionSpacer(fraction = 0.5F)
            ResourceText(id = R.string.sign_in_screen_submit_button)
        }
    }
}
