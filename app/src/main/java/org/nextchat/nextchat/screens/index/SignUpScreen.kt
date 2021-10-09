package org.nextchat.nextchat.screens.index

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavController
import org.nextchat.nextchat.repositories.index.SignUpRepository
import org.nextchat.nextchat.widgets.ErrorText
import org.nextchat.nextchat.R
import org.nextchat.nextchat.layouts.IndexLayout
import org.nextchat.nextchat.widgets.Spacer
import org.nextchat.nextchat.widgets.Text

@Composable
fun SignUpScreen(
    accountStorage: DataStore<Preferences>,
    navController: NavController,
) {
    // States
    var username by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var showingPassword by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf("") }

    var repeatPassword by remember { mutableStateOf("") }
    var showingRepeatPassword by remember { mutableStateOf(false) }
    var repeatPasswordError by remember { mutableStateOf("") }

    // Hooks
    val context = LocalContext.current
    val signUpScope = rememberCoroutineScope()

    // Content
    IndexLayout(title = stringResource(R.string.sign_up_title)) {
        // Inputs
        OutlinedTextField(
            isError = usernameError.isNotEmpty(),
            label = { androidx.compose.material.Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = {
                username = it
                usernameError = ""
            }
        )
        ErrorText(message = usernameError)
        Spacer(Modifier.height(6.dp))
        OutlinedTextField(
            isError = passwordError.isNotEmpty(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            label = { androidx.compose.material.Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(
                    onClick = { showingPassword = !showingPassword }
                ) {
                    Icon(
                        imageVector = if (showingPassword) { Icons.Filled.VisibilityOff } else { Icons.Filled.Visibility },
                        contentDescription = if (showingPassword) { "Hide password" } else { "Show password" }
                    )
                }
            },
            value = password,
            visualTransformation = if (showingPassword) { VisualTransformation.None } else { PasswordVisualTransformation() },
            onValueChange = {
                password = it
                passwordError = ""
            }
        )
        ErrorText(message = passwordError)
        Spacer(Modifier.height(6.dp))
        OutlinedTextField(
            isError = repeatPasswordError.isNotEmpty(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            label = { androidx.compose.material.Text("Repeat password") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(
                    onClick = { showingRepeatPassword = !showingRepeatPassword }
                ) {
                    Icon(
                        imageVector = if (showingRepeatPassword) { Icons.Filled.VisibilityOff } else { Icons.Filled.Visibility },
                        contentDescription = if (showingRepeatPassword) { "Hide repeat password" } else { "Show repeat password" }
                    )
                }
            },
            value = repeatPassword,
            visualTransformation = if (showingRepeatPassword) { VisualTransformation.None } else { PasswordVisualTransformation() },
            onValueChange = {
                repeatPassword = it
                repeatPasswordError = ""
            }
        )
        ErrorText(message = repeatPasswordError)
        Spacer(Modifier.height(6.dp))

        // Information
        Text(
            stringResource = stringResource(id = R.string.AUTH_PRIVACY_POLICY), color = Color.Gray,
            fontSize = MaterialTheme.typography.subtitle2.fontSize,
            modifier = Modifier.padding(start = 2.dp))
        Spacer(Modifier.height(36.dp))
        // Buttons
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Sign in button
            TextButton(
                modifier = Modifier.fillMaxWidth(0.6F),
                onClick = {}
            ) {
                Text(
                    text = "I have an account",
                    textAlign = TextAlign.Center
                )
            }
            // Submit button
            Button(
                onClick = {
                    SignUpRepository().handleSubmit(
                        // Fields
                        username = username,
                        password = password,
                        confirmPassword = repeatPassword,
                        // General
                        accountStorage = accountStorage,
                        context = context,
                        navController = navController,
                        scope = signUpScope,
                        // Callbacks
                        onErrors = { errors ->
                            // Reset errors
                            usernameError = ""
                            passwordError = ""
                            repeatPassword = ""

                            // Set new errors
                            for ((field, message) in errors.iterator()) {
                                when (field) {
                                    "username" -> { usernameError = message }
                                    "password" -> { passwordError = message }
                                    "repeat_password" -> { repeatPasswordError = message }
                                    else -> { println("Cannot parse: $field as error (Message: $message)") }
                                }
                            }
                        }
                    )
                }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowRight, "")
                    Spacer(modifier = Modifier.width(6.dp))
                    androidx.compose.material.Text("Sign up")
                }
            }
        }
    }
}
