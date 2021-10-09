package org.nextchat.nextchat.screens.index

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.draw.clip
import org.nextchat.nextchat.core.Screens
import org.nextchat.nextchat.layouts.IndexLayout

@Composable
fun RecoveryCodesScreen(
    codes: List<String>,
    navController: NavController
) {
    IndexLayout(
        title = "Recover codes",
        titleMargin = 24.dp
    ) {
        // Description
        Text(
            text = "Please take a screenshot to save the codes below, you could use them to recover your account when you forget its password.",
            modifier = Modifier.padding(start = 3.dp, end = 3.dp),
            style = MaterialTheme.typography.caption,
        )
        Spacer(modifier = Modifier.height(12.dp))
        // Codes
        codes.forEach { code ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFEEEEEE))
                    .padding(12.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Text(text = code)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Buttons
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                navController.navigate(route = Screens.HomeScreen.route) {
                    popUpTo(route = Screens.HomeScreen.route) {
                        inclusive = true
                    }
                }
            }) {
                Text(text = "Continue")
            }
        }
    }
}
