package org.nextchat.nextchat.screens.index

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.nextchat.nextchat.widgets.NextChatLogo

@Composable
fun SignUpScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        // Logo
        NextChatLogo()
        Spacer(
            modifier = Modifier
                .width(112.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.height(12.dp))
        // Title
        Text(
            "Get started",
            color = Color.Gray,
            fontSize = 8.em,
            fontWeight = FontWeight.W400
        )
        Spacer(modifier = Modifier.height(36.dp))
        // Inputs
        OutlinedTextField(
            label = {
                Text("Username")
            },
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = { }
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            label = {
                Text("Password")
            },
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {}
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            label = {
                Text("Repeat password")
            },
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {}
        )
        Spacer(modifier = Modifier.height(6.dp))
        // Information
        Text(
            "By clicking the button below you will be agree to the Terms of Service and Privacy Policy.",
            color = Color.Gray,
            fontSize = MaterialTheme.typography.subtitle2.fontSize,
            modifier = Modifier.padding(start = 2.dp)
        )
        Spacer(modifier = Modifier.height(36.dp))
        // Buttons
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {}) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowRight, "")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Sign up")
                }
            }
        }
    }
}
