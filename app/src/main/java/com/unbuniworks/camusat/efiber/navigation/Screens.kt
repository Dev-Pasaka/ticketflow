package com.unbuniworks.camusat.efiber.navigation

sealed class Screen(val route:String){
    //Auth Screens
    data object SplashScreen: Screen("splash_screen")
    data object LoginScreen: Screen("login_screen")
    data object SelectModule: Screen("select_module_screen")

    //Bottom Navigation Screen
    data object Container: Screen("container_screen")
    data object Home: Screen("home_screen")
    data object Projects: Screen("projects_screen")
    data object Tickets: Screen("tickets_screen")
    data object Requests: Screen("requests_screen")
    data object More: Screen("more_screen")

    //Other screens
    data object TicketInformation: Screen("ticket_information_screen")


}