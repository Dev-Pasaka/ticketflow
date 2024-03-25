package com.unbuniworks.camusat.efiber.presentation.navigation

sealed class Screen(val route:String){
    //Auth Screens
    data object SplashScreen: Screen("splash_screen")
    data object LoginScreen: Screen("login_screen")
    data object SelectModule: Screen("select_module_screen")

    //Bottom Navigation Screen
    data object Tickets: Screen("tickets")
    data object Schedule: Screen("schedule")
    data object Material: Screen("material")
    data object More: Screen("more")


    //Other screens
    data object TicketInformation: Screen("ticket_information_screen")
    data object Notifications: Screen("notifications")
    data object Profile: Screen("profile")

    //Camera
    data object TakePhoto: Screen("take_photo")
    data object Maps: Screen("maps/{index}")


}