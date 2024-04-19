package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.ProfileScreenViewModel

@Composable
fun ProfileScreenUpperSection(navController:NavHostController, profileScreenViewModel: ProfileScreenViewModel) {
    val context = LocalContext.current
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 2.dp, end = 16.dp, top = 26.dp)
    ){

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconButton(onClick = {
                navController.navigate(Screen.Tickets.route){
                navController.popBackStack()
            } }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "go back",
                    tint = colorResource(id = R.color.button_color),
                    modifier = Modifier
                        .offset(x = (-10).dp)
                        .size(20.dp)
                )
            }
            Text(
                text = "Profile",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorResource(id = R.color.button_color)
            )
        }

        IconButton(
            onClick = {
                      profileScreenViewModel.getUser(context)
            },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = colorResource(id = R.color.light_blue),
                contentColor = Color.White,
            ),
            modifier = Modifier


        ) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
        }



    }
}