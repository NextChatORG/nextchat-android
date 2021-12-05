package org.nextchat.nextchat.ui.screens.index

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.nextchat.nextchat.R
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.ui.common.*
import org.nextchat.nextchat.ui.screens.Navigator
import org.nextchat.nextchat.ui.viewmodels.index.SignInViewModel

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel()
) {
    // States
    var visible by remember { mutableStateOf(false) }

    // Hooks
    val navController = Navigator.current

    // Effects
    LaunchedEffect(true) {
        visible = true
    }

    // Content
    Scaffold(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        // Background image
        Image(
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6F),
            painter = painterResource(id = R.drawable.sign_in_background),
        )
        // NextChat logo
        Logo(visible)
        // Slogan
        Slogan(visible)
        // Form
        FormContent(navController, viewModel, visible)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Logo(
    visible: Boolean
) {
    // Animations
    val logoContentEnterTransition = slideInVertically(
        animationSpec = tween(500)
    )

    // Content
    AnimatedVisibility(
        visible = visible,
        enter = logoContentEnterTransition,
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp
                ),
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
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Slogan(
    visible: Boolean,
) {
    // Animations
    val sloganContentEnterTransition = fadeIn(
        animationSpec = tween(500, 250)
    )

    // Content
    Column(
        modifier = Modifier.fillMaxHeight(0.5F),
        verticalArrangement = Arrangement.Center,
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = sloganContentEnterTransition,
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
                    color = Color.White,
                    id = R.string.slogan,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun FormContent(
    navController: NavHostController,
    viewModel: SignInViewModel,
    visible: Boolean
) {
    // Animations
    val formContentEnterTransition = slideInVertically(
        initialOffsetY = { 250 },
        animationSpec = tween(500)
    )

    // Content
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = formContentEnterTransition,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(color = MaterialTheme.colors.onPrimary)
                    .padding(vertical = 24.dp, horizontal = 16.dp),
            ) {
                // Form title
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
                    error = viewModel.usernameError,
                    label = R.string.sign_up_screen_username_input,
                    onValueChange = viewModel.handleUsernameChange(),
                    value = viewModel.username,
                )
                // Password input
                PasswordInput(
                    error = viewModel.passwordError,
                    label = R.string.sign_up_screen_password_input,
                    onValueChange = viewModel.handlePasswordChange(),
                    space = 0F,
                    value = viewModel.password
                )
                // Forgot password button
                ForgotPasswordButton(navController)
                FractionSpacer(2F)
                // Submit button
                SignInButton(navController, viewModel)
                FractionSpacer(0.2F)
                // Sign up button
                SignUpButton(navController)
            }
        }
    }
}

@Composable
private fun ForgotPasswordButton(
    navController: NavHostController
) {
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
    navController: NavHostController,
    viewModel: SignInViewModel
) {
    androidx.compose.material3.Button(
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colors.primary,
        ),
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            viewModel.submit {
                navController.navigate(Screens.Splash.route) {
                    navController.clearBackStack(Screens.Splash.route)
                }
            }
        }
    ) {
        if (viewModel.loading) {
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
private fun SignUpButton(
    navController: NavHostController
) {
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
