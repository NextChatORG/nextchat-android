package org.nextchat.nextchat.ui.screens

sealed class Screens(val route: String) {
    // General screens
    object Splash : Screens(route = "splash")

    // Index screens
    object Welcome : Screens(route = "welcome")
    object SignIn : Screens(route = "sign_in")
    object SignUp : Screens(route = "sign_up")
    object RecoveryCodes : Screens(route = "recovery_codes")

    // Home screens
    object Home : Screens(route = "home")

    // Methods
    fun withArguments(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}