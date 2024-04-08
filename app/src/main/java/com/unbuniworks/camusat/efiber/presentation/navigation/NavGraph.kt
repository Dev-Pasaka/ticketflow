package com.unbuniworks.camusat.efiber.presentation.navigation

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.domain.usecase.LoginUseCase
import com.unbuniworks.camusat.efiber.domain.usecase.MaterialsUseCase
import com.unbuniworks.camusat.efiber.domain.usecase.WorkOrdersUseCase
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.camerea.Camera
import com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.LoginScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.LoginsScreen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.Material
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.MaterialScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.more.MoreScreen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.more.MoreScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications.NotificationsScreen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications.NotificationsViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.ProfileScreen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.ProfileScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.Schedule
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.ScheduleScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.TicketsScreen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.TicketsScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.selecteModule.SelectModule
import com.unbuniworks.camusat.efiber.presentation.ui.screens.selecteModule.SelectModuleViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.splashScreen.SplashScreen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationScreen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel


@RequiresApi(Build.VERSION_CODES.O)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    val bottomNavigationViewModel = viewModel<BottomNavigationViewModel>()

    val scope = rememberCoroutineScope()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        navigation(
            startDestination = Screen.LoginScreen.route,
            route = "auth"
        ) {
            composable(route = Screen.LoginScreen.route) {

                val loginScreenViewModel = viewModel<LoginScreenViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return LoginScreenViewModel(
                                loginUseCase = LoginUseCase()
                            ) as T
                        }
                    }
                )

                LoginsScreen(loginScreenViewModel = loginScreenViewModel) {
                    loginScreenViewModel.login(navController, activity)

                    }
                }

                composable(route = Screen.SelectModule.route) {
                    val selectModuleViewModel = viewModel<SelectModuleViewModel>()
                    SelectModule(selectModuleViewModel = selectModuleViewModel) {
                        navController.navigate(route = "bottom_navigation") {
                            navController.popBackStack()
                        }
                    }
                }
            }

            navigation(startDestination = Screen.Tickets.route, route = "bottom_navigation") {


                composable(route = Screen.Tickets.route) {
                    val ticketsScreenViewModel = viewModel<TicketsScreenViewModel>(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return TicketsScreenViewModel(
                                    useCase = WorkOrdersUseCase()
                                ) as T
                            }
                        }
                    )
                    TicketsScreen(
                        bottomNavigationViewModel = bottomNavigationViewModel,
                        ticketsScreenViewModel = ticketsScreenViewModel,
                        navController = navController
                    )
                }
                composable(route = Screen.Schedule.route) {
                    val scheduleScreenViewModel = viewModel<ScheduleScreenViewModel>()
                    Schedule(
                        bottomNavigationViewModel = bottomNavigationViewModel,
                        scheduleScreenViewModel = scheduleScreenViewModel,
                        navController = navController
                    )
                }
                composable(route = Screen.Material.route) {
                    val materialScreenViewModel = viewModel<MaterialScreenViewModel>(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return MaterialScreenViewModel(
                                    useCase = MaterialsUseCase()
                                ) as T
                            }
                        }
                    )
                    Material(
                        bottomNavigationViewModel = bottomNavigationViewModel,
                        navController = navController,
                        materialScreenViewModel = materialScreenViewModel
                    )
                }
                composable(route = Screen.More.route) {
                    val moreScreenViewModel = viewModel<MoreScreenViewModel>()
                    MoreScreen(
                        bottomNavigationViewModel = bottomNavigationViewModel,
                        navController = navController,
                        moreScreenViewModel = moreScreenViewModel
                    )
                }

            }

            navigation(
                startDestination = Screen.TicketInformation.route,
                route = "ticket_information"
            ) {
                composable(route = Screen.TicketInformation.route) {
                    val workOrderId = navController.previousBackStackEntry?.arguments?.getString("workOrderId") ?: ""

                    val ticketInformationViewModel = viewModel<TicketInformationViewModel>(
                        factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return TicketInformationViewModel(
                                    orderId = workOrderId
                                ) as T
                            }
                        }
                    )

                    Log.e("workOrderId", workOrderId)

                    TicketInformationScreen(
                        navController = navController,
                        ticketInformationViewModel = ticketInformationViewModel,
                        workOrder = workOrderId
                    )
                }
                composable(route = Screen.TakePhoto.route) {
                    /*Camera(
                        index = ticketInformationViewModel.selectedImageAndIndex?.first ?: 0,
                        imageIndex = ticketInformationViewModel.selectedImageAndIndex?.second ?: 0,
                        navController = navController,
                        ticketInformationViewModel = ticketInformationViewModel
                    )*/
                }
                composable(route = Screen.Maps.route) { backStackEntry ->
                    val index = backStackEntry.arguments?.getString("index")
                   /* MapsAndLocation(
                        navController = navController,
                        index = index ?: "0",
                        ticketInformationViewModel = ticketInformationViewModel
                    )*/
                }
            }

            navigation(
                startDestination = Screen.Notifications.route,
                route = "notification_screen"
            ) {
                composable(route = Screen.Notifications.route) {
                    val notificationsViewModel = viewModel<NotificationsViewModel>()

                    NotificationsScreen(
                        navController = navController,
                        notificationsViewModel = notificationsViewModel
                    )
                }
            }

            navigation(startDestination = Screen.Profile.route, route = "profile_screen") {
                composable(route = Screen.Profile.route) {
                    val profileScreenViewModel = viewModel<ProfileScreenViewModel>()
                    ProfileScreen(
                        navController = navController,
                        profileScreenViewModel = profileScreenViewModel
                    )
                }
            }


        }
    }