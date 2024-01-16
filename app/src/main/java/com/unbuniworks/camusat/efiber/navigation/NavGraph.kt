package com.unbuniworks.camusat.efiber.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.unbuniworks.camusat.efiber.ui.screens.auth.LoginScreenViewModel
import com.unbuniworks.camusat.efiber.ui.screens.auth.LoginsScreen
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.material.Material
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.material.MaterialScreenViewModel
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more.MoreScreen
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more.MoreScreenViewModel
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.profile.ProfileScreen
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.schedule.Schedule
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets.TicketsScreen
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets.TicketsScreenViewModel
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.notifications.NotificationsScreen
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.notifications.NotificationsViewModel
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.profile.ProfileScreenViewModel
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.schedule.ScheduleScreenViewModel
import com.unbuniworks.camusat.efiber.ui.screens.selecteModule.SelectModule
import com.unbuniworks.camusat.efiber.ui.screens.selecteModule.SelectModuleViewModel
import com.unbuniworks.camusat.efiber.ui.screens.splashScreen.SplashScreen
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.TicketInformationScreen
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.TicketInformationViewModel
import kotlinx.coroutines.launch

@Composable
fun NavGraph(navController: NavHostController) {
    val bottomNavigationViewModel = viewModel<BottomNavigationViewModel>()
    val scope = rememberCoroutineScope()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {


        composable(route = Screen.SplashScreen.route){
            SplashScreen(navController = navController)
        }

        navigation(
            startDestination = Screen.LoginScreen.route,
            route = "auth"
        ) {
            composable(route = Screen.LoginScreen.route) {
                val loginScreenViewModel = viewModel<LoginScreenViewModel>()

                LoginsScreen(loginScreenViewModel = loginScreenViewModel){
                    scope.launch {
                        loginScreenViewModel.login()
                        if (loginScreenViewModel.loginState.isSuccessful){
                            navController.navigate(route = Screen.SelectModule.route){
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }

            composable(route = Screen.SelectModule.route) {
                val selectModuleViewModel = viewModel<SelectModuleViewModel>()
                SelectModule(selectModuleViewModel = selectModuleViewModel){
                    navController.navigate(route = "bottom_navigation"){
                        navController.popBackStack()
                    }
                }
            }
        }

        navigation(startDestination = Screen.Tickets.route, route = "bottom_navigation"){


            composable(route = Screen.Tickets.route){
                val ticketsScreenViewModel = viewModel<TicketsScreenViewModel>()
                TicketsScreen(
                    bottomNavigationViewModel = bottomNavigationViewModel,
                    ticketsScreenViewModel = ticketsScreenViewModel,
                    navController = navController
                ){
                    navController.navigate(route = "ticket_information")
                }            }
            composable(route = Screen.Schedule.route){
                val scheduleScreenViewModel = viewModel<ScheduleScreenViewModel>()
                Schedule(bottomNavigationViewModel = bottomNavigationViewModel, scheduleScreenViewModel = scheduleScreenViewModel, navController = navController)
            }
            composable(route = Screen.Material.route){
                val materialScreenViewModel = viewModel<MaterialScreenViewModel>()
                Material(
                    bottomNavigationViewModel = bottomNavigationViewModel,
                    navController = navController,
                    materialScreenViewModel = materialScreenViewModel
                )
            }
            composable(route = Screen.More.route){
                val moreScreenViewModel = viewModel<MoreScreenViewModel>()
                MoreScreen(
                    bottomNavigationViewModel = bottomNavigationViewModel,
                    navController = navController,
                    moreScreenViewModel = moreScreenViewModel
                )
            }

        }

        navigation(startDestination = Screen.TicketInformation.route, route = "ticket_information"){
            composable(route = Screen.TicketInformation.route){
                val ticketInformationViewModel = viewModel<TicketInformationViewModel>()
                TicketInformationScreen(navController = navController, ticketInformationViewModel = ticketInformationViewModel)
            }
        }

        navigation(startDestination = Screen.Notifications.route, route = "notification_screen"){
            composable(route = Screen.Notifications.route){
                val notificationsViewModel = viewModel<NotificationsViewModel>()

                NotificationsScreen(navController = navController, notificationsViewModel = notificationsViewModel)
            }
        }

        navigation(startDestination = Screen.Profile.route, route = "profile_screen"){
            composable(route = Screen.Profile.route){
                val profileScreenViewModel = viewModel<ProfileScreenViewModel>()

                ProfileScreen(navController = navController, profileScreenViewModel = profileScreenViewModel)
            }
        }


    }
}