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
fun ForgotPasswordScreen(
) {
    IndexLayout(
        title = R.string.sign_in_screen_forgot_password_button,
        titleMargin = 2F
    ) {
        // Description

       Text(text = "Lorem ipsum")
    }
}

