package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.notifications.NotificationsViewModel

@Composable
fun NotificationsScreenUpperSection(navController:NavHostController, notificationsViewModel: NotificationsViewModel){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 2.dp, end = 16.dp, top = 68.dp)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = {
                navController.navigate(Screen.Tickets.route) {
                    navController.popBackStack()
                }
            }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "go back",
                    tint = colorResource(id = R.color.button_color),
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = "${stringResource(id = R.string.notifications_lable)} (${notificationsViewModel.notifications.size})",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorResource(id = R.color.button_color)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))


        Surface(
            onClick = {},
            color = colorResource(id = R.color.light_blue),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.height(30.dp)

        ) {
           Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(8.dp),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally,

           ) {
               Text(
                   text = stringResource(id = R.string.clear_notifications),
                   fontSize = 12.sp,
                   color = colorResource(id = R.color.button_color)
               )
           }
        }
    }
}