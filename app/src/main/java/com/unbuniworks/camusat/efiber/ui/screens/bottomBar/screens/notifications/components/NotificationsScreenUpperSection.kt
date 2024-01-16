package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.notifications.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.navigation.Screen
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.notifications.NotificationsViewModel

@Composable
fun NotificationsScreenUpperSection(navController:NavHostController, notificationsViewModel: NotificationsViewModel){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 2.dp,end = 16.dp,  top = 48.dp)
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
                text = "Notifications ( ${notificationsViewModel.notifications.size} )",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorResource(id = R.color.button_color)
            )
        }
    }
}