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
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.home.HomeScreen
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.more.MoreScreen
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.projects.ProjectsScreen
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.requests.RequestsScreen
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets.TicketsScreen
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets.TicketsScreenViewModel
import com.unbuniworks.camusat.efiber.ui.screens.selecteModule.SelectModule
import com.unbuniworks.camusat.efiber.ui.screens.selecteModule.SelectModuleViewModel
import kotlinx.coroutines.launch

@Composable
fun NavGraph(navController: NavHostController) {
    val bottomNavigationViewModel = viewModel<BottomNavigationViewModel>()
    NavHost(navController = navController, startDestination = "auth") {


        navigation(
            startDestination = Screen.LoginScreen.route,
            route = "auth"
        ) {
            composable(route = Screen.LoginScreen.route) {
                val loginScreenViewModel = viewModel<LoginScreenViewModel>()
                val scope = rememberCoroutineScope()

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
        navigation(startDestination = Screen.Home.route, route = "bottom_navigation"){


            composable(route = Screen.Home.route){
                HomeScreen(bottomNavigationViewModel = bottomNavigationViewModel, navController = navController)
            }
            composable(route = Screen.Projects.route){
                ProjectsScreen(bottomNavigationViewModel = bottomNavigationViewModel, navController = navController)
            }
            composable(route = Screen.Tickets.route){
                val ticketsScreenViewModel = viewModel<TicketsScreenViewModel>()
                TicketsScreen(
                    bottomNavigationViewModel = bottomNavigationViewModel,
                    ticketsScreenViewModel = ticketsScreenViewModel,
                    navController = navController
                )
            }
            composable(route = Screen.Requests.route){
                RequestsScreen(bottomNavigationViewModel = bottomNavigationViewModel, navController = navController)
            }
            composable(route = Screen.More.route){
                MoreScreen(bottomNavigationViewModel = bottomNavigationViewModel, navController = navController)
            }

        }
    }
}