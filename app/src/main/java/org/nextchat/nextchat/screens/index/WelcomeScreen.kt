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
import org.nextchat.nextchat.core.Screens
import org.nextchat.nextchat.widgets.NextChatLogo

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
            Image(
                painterResource(R.drawable.ic_begin_chat),
                contentDescription = "Begin chat image",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(84.dp))
            // NextChat logo
            NextChatLogo()
            Spacer(modifier = Modifier.height(12.dp))
            // Extra message
            Text(
                text = stringResource(id = R.string.slogan),
                color = Color.Gray,
                fontSize = 6.em,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(48.dp))
            // Buttons
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate(route = Screens.SignUpScreen.route) }
            ) {
                Text(text = stringResource(id = R.string.welcome_screen_sign_up_button))
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(route = Screens.HomeScreen.route)
                }
            ) {
                Text(text = stringResource(id = R.string.welcome_screen_sign_in_button))
            }
        }
}
