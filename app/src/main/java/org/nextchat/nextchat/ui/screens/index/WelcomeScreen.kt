package org.nextchat.nextchat.ui.screens.index

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import org.nextchat.nextchat.R
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.ui.widgets.FractionSpacer
import org.nextchat.nextchat.ui.widgets.NextChatLogo
import org.nextchat.nextchat.ui.widgets.ResourceText

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Image
        BeginImage()
        FractionSpacer(fraction = 7F)
        // NextChat logo
        NextChatLogo()
        FractionSpacer()
        // Extra message
        ResourceText(
            id = R.string.slogan,
            color = Color.Gray,
            fontSize = 6.em,
            textAlign = TextAlign.Center,
        )
        FractionSpacer(fraction = 4F)
        // Buttons
        SignUpButton(navController)
        FractionSpacer()
        SignInButton(navController)
    }
}

@Composable
private fun BeginImage() {
    Image(
        painterResource(R.drawable.ic_begin_chat),
        contentDescription = "Begin chat BeginImage",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun SignUpButton(
    navController: NavController
){
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { navController.navigate(route = Screens.SignUp.route) }
    ) {
        ResourceText(id = R.string.welcome_screen_sign_up_button)
    }
}

@Composable
private fun SignInButton(
    navController: NavController
) {
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { navController.navigate(route = Screens.SignIn.route) }
    ) {
        ResourceText(id = R.string.welcome_screen_sign_in_button)
    }
}
