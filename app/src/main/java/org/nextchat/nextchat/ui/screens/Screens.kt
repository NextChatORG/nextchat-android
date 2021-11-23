package org.nextchat.nextchat.ui.screens

sealed class Screens(val route: String) {
    // General screens
    object Splash : Screens(route = "splash")
    object Home : Screens(route = "home")

    // Index screens
    object SignIn : Screens(route = "sign_in")
    object SignUp : Screens(route = "sign_up")
    object ForgotPassword : Screens(route = "forgot_password")
    object RecoveryCodes : Screens(route = "recovery_codes")

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