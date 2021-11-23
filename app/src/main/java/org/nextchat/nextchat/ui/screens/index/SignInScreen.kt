package org.nextchat.nextchat.ui.screens.index

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import org.nextchat.nextchat.R
import org.nextchat.nextchat.constants.AccountStorage
import org.nextchat.nextchat.models.index.SignInViewModel
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.ui.widgets.*

@Composable
fun SignInScreen(
    accountStorage: AccountStorage,
    navController: NavController,
    signInViewModel: SignInViewModel
) {
    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        ConstraintLayout(Modifier.fillMaxHeight()) {
            val (backgroundImage, logoContent, formContent) = createRefs()

            // Background image
            Image(
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .constrainAs(backgroundImage) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(0.6F),
                painter = painterResource(id = R.drawable.sign_in_background),
            )
            // NextChat logo
            Row(
                modifier = Modifier
                    .constrainAs(logoContent) {
                        top.linkTo(parent.top, margin = 16.dp)
                    }
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(R.drawable.ic_nextchat_icon),
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    color = Color.White,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Medium,
                    text = "NextChat"
                )
            }
            // Slogan
            Column(
                modifier = Modifier.fillMaxHeight(0.5F),
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .background(
                            color = MaterialTheme.colors.primary.copy(alpha = 0.7F),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    ResourceText(
                        color = MaterialTheme.colors.onPrimary,
                        id = R.string.slogan,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
            // Form
            FormContent(
                accountStorage,
                navController,
                Modifier
                    .fillMaxWidth()
                    .constrainAs(formContent) {
                        bottom.linkTo(parent.bottom)
                    }
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(color = MaterialTheme.colors.onPrimary)
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                signInViewModel,
            )
        }
    }
}

@Composable
private fun FormContent(
    accountStorage: AccountStorage,
    navController: NavController,
    modifier: Modifier,
    signInViewModel: SignInViewModel,
) {
    Column(modifier) {
        // Form title
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                contentDescription = "Person icon",
                imageVector = Icons.Filled.Person,
                tint = MaterialTheme.colors.primary,
            )
            Spacer(Modifier.width(12.dp))
            ResourceText(
                id = R.string.sign_in_screen_title,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Medium,
                style = TextStyle(
                    color = MaterialTheme.colors.primary,
                ),
            )
        }
        FractionSpacer(2F)
        // Username input
        TextInput(
            error = signInViewModel.usernameError,
            label = R.string.sign_up_screen_username_input,
            onValueChange = { signInViewModel.handleChangeUsername(it) },
            value = signInViewModel.username,
        )
        // Password input
        PasswordInput(
            error = signInViewModel.passwordError,
            label = R.string.sign_up_screen_password_input,
            onValueChange = { signInViewModel.handleChangePassword(it) },
            space = 0F,
            value = signInViewModel.password
        )
        // Forgot password button
        ForgotPasswordButton(navController)
        FractionSpacer(2F)
        // Submit button
        SignInButton(accountStorage, navController, signInViewModel)
        FractionSpacer(0.2F)
        // Sign up button
        SignUpButton(navController)
    }
}

@Composable
private fun ForgotPasswordButton(navController: NavController) {
    TextButton(
        onClick = { navController.navigate(route = Screens.ForgotPassword.route) }
    ) {
        ResourceText(
            color = MaterialTheme.colors.onBackground,
            id = R.string.sign_in_screen_forgot_password_button,
            fontSize = MaterialTheme.typography.caption.fontSize,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun SignInButton(
    accountStorage: AccountStorage,
    navController: NavController,
    signInViewModel: SignInViewModel,
) {
    // Hooks
    val context = LocalContext.current

    // Content
    androidx.compose.material3.Button(
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colors.primary,
        ),
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (!signInViewModel.isLoading) {
                signInViewModel.submit(accountStorage, navController, context)
            }
        }
    ) {
        if (signInViewModel.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.size(24.dp)
            )
        } else {
            ResourceText(
                color = MaterialTheme.colors.onPrimary,
                id = R.string.sign_in_screen_submit_button,
            )
        }
    }
}

@Composable
private fun SignUpButton(navController: NavController) {
    androidx.compose.material3.TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { navController.navigate(route = Screens.SignUp.route) }
    ) {
        ResourceText(
            id = R.string.sign_in_screen_sign_up_button,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )
    }
}
