package com.unbuniworks.camusat.efiber.presentation.ui.commonComponents

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.SubmitUserLogsDto
import com.unbuniworks.camusat.efiber.domain.usecase.GetAllNotificationUseCase
import com.unbuniworks.camusat.efiber.domain.usecase.SubmitLogsUseCase
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun TopAppBar(
    actionOpenNavDrawer: () -> Unit,
    navController: NavHostController,
) {
    val sharedPreferenceRepositoryImpl = SharedPreferenceRepositoryImpl()
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()

    var isDropDownOpen by remember {
        mutableStateOf(false)
    }

    var notificationCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        GetAllNotificationUseCase().getAllNotification(activity).collect {
            notificationCount = it.data?.size ?: 0
        }
    }
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center,
        //   modifier = Modifier.padding(bottom = 16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {


            Image(
                painter = painterResource(id = R.drawable.ticket_flow_logo),
                contentDescription = "person Icon",
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .offset(x = (-30).dp)
                    .height(25.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {



                if (notificationCount > 0) {
                   IconButton(
                       onClick = {
                           navController.navigate(Screen.Notifications.route) {
                               navController.popBackStack()
                           }
                       }
                   ) {
                       Image(
                           painter = painterResource(id = R.drawable.notification_on_icon_with_red),
                           contentDescription = "Language Icon",
                           modifier = Modifier.size(25.dp)
                       )
                   }

                }else{
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Notifications.route) {
                                navController.popBackStack()
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.notification_on_icon),
                            contentDescription = "Language Icon",
                            modifier = Modifier.size(25.dp)

                        )
                    }
                }

                Spacer(modifier = Modifier.width(1.dp))


                IconButton(
                    onClick = { isDropDownOpen = !isDropDownOpen }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.person_icon),
                        contentDescription = "person Icon",
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .size(55.dp)
                            .padding(8.dp)
                    )
                }


            }
        }

        DropdownMenu(
            expanded = isDropDownOpen, onDismissRequest = { isDropDownOpen = !isDropDownOpen },
            offset = DpOffset(x = 280.dp, y = (-20).dp),
            modifier = Modifier.background(Color.White),
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.profile_screen)) },
                onClick = {
                    navController.navigate(Screen.Profile.route) {
                        navController.popBackStack()
                    }
                    isDropDownOpen = !isDropDownOpen
                }
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.logout_screen)) },
                onClick = {
                    scope.launch {
                        isDropDownOpen = !isDropDownOpen
                        sharedPreferenceRepositoryImpl.setString(
                            key = Constants.isLoggedIn,
                            value = "false",
                            activity = context as Activity
                        )
                        val result = SubmitLogsUseCase().submitLogs(
                            logs = SubmitUserLogsDto(
                                module = "Auth",
                                activity = "Log out",
                                detail = "User has logged out"
                            ),
                            activity = context as Activity
                        )
                        Log.e("Log out", result.toString())
                        navController.navigate("auth") {
                            popUpTo("auth") { inclusive = false }
                        }
                    }
                }
            )
        }
    }
}


