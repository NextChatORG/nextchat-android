package org.nextchat.nextchat.ui.screens.index

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.nextchat.nextchat.ui.layouts.IndexLayout
import org.nextchat.nextchat.R
import org.nextchat.nextchat.ui.screens.Screens
import org.nextchat.nextchat.ui.widgets.FractionSpacer
import org.nextchat.nextchat.ui.widgets.ResourceText

@Composable
fun RecoveryCodesScreen(
    codes: List<String>,
    navController: NavController
) {
    IndexLayout(
        title = R.string.recovery_codes_screen_title,
        titleMargin = 2F
    ) {
        // Description
        RecoveryMessage()
        FractionSpacer()
        // Codes
        codes.forEach { code ->
            BoxCode(code)
            FractionSpacer(fraction = 0.5F)
        }
        FractionSpacer(fraction = 1.5F)
        // Buttons
        ButtonBox(navController)
    }
}

@Composable
private fun RecoveryMessage() {
    ResourceText(
        id = R.string.AUTH_SCREENSHOT_CODES,
        modifier = Modifier.padding(start = 3.dp, end = 3.dp),
        style = MaterialTheme.typography.caption,
    )
}

@Composable
private fun BoxCode(code: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFFEEEEEE))
            .padding(12.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Text(text = code)
    }
}

@Composable
private fun ButtonBox(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            navController.navigate(route = Screens.Home.route) {
                popUpTo(route = Screens.Home.route) {
                    inclusive = true
                }
            }
        }) {
            ResourceText(id = R.string.AUTH_CODE_TEXT_CONTINUE)
        }
    }
}
