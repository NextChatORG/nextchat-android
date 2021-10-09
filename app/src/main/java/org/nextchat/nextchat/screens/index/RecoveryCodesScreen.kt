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
import androidx.ui.res.stringResource
import org.nextchat.nextchat.screens.Screens
import org.nextchat.nextchat.layouts.IndexLayout
import org.nextchat.nextchat.R
import org.nextchat.nextchat.widgets.Spacer
import org.nextchat.nextchat.widgets.Text

@Composable
fun RecoveryCodesScreen(
    codes: List<String>,
    navController: NavController
) {
    IndexLayout(
        title = "Recover codes",
        titleMargin = 24.dp
    )
    {
        // Description
        RecoveryMessage()
        Spacer(Modifier.height(12.dp))
        // Codes
        codes.forEach { code ->
            BoxCode(code)
            Spacer(Modifier.height(8.dp))
        }
        Spacer(Modifier.height(16.dp))
        // Buttons
        ButtonBox(navController)
    }
}

@Composable
fun RecoveryMessage(){
    Text(stringResource = stringResource(id = R.string.AUTH_SCREENSHOT_CODES),
        modifier = Modifier.padding(start = 3.dp, end = 3.dp),
        style = MaterialTheme.typography.caption)
}

@Composable
fun BoxCode(code: String) {
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
fun ButtonBox(navController: NavController) {
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
            Text(stringResource = stringResource(id = R.string.AUTH_CODE_TEXT_CONTINUE))
        }
    }
}
