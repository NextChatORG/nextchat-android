package org.nextchat.nextchat.screens.index

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import org.nextchat.nextchat.R
import org.nextchat.nextchat.screens.Screens
import org.nextchat.nextchat.widgets.NextChatLogo
import org.nextchat.nextchat.widgets.Spacer
import org.nextchat.nextchat.widgets.Text

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
            Spacer(Modifier.height(84.dp))
            // NextChat logo
            NextChatLogo()
            Spacer(Modifier.height(12.dp))
            // Extra message
            Text(stringResource(id = R.string.slogan),
                color = Color.Gray,
                fontSize = 6.em,
                textAlign = TextAlign.Center)
            Spacer(Modifier.height(48.dp))
            // Buttons
            ButtonNewHere(navController)
            Spacer(Modifier.height(12.dp))
            ButtonSignIn(navController)
        }
}


@Composable
fun BeginImage() {
    Image(
        painterResource(R.drawable.ic_begin_chat),
        contentDescription = "Begin chat BeginImage",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ButtonNewHere(navController: NavController){
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { navController.navigate(route = Screens.SignUpScreen.route) }
    ) {
        Text(text = stringResource(id = R.string.welcome_screen_sign_up_button))
    }
}
@Composable
fun ButtonSignIn(navController: NavController) {
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            navController.navigate(route = Screens.HomeScreen.route)
        }
    ) {
        Text(text = stringResource(id = R.string.welcome_screen_sign_in_button))
    }
}
