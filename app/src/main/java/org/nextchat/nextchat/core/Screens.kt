package org.nextchat.nextchat.core

sealed class Screens(val route: String) {
    // Index screens
    object WelcomeScreen : Screens("welcome")
    object SignUpScreen : Screens("sign_up")
    object RecoveryCodesScreen : Screens("recovery_codes")

    // Home screens
    object HomeScreen : Screens("home")

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
