package com.unbuniworks.camusat.efiber.presentation.ui.commonComponents

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun TopAppBar(navController: NavHostController, sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()) {

    var isProfileDropDown by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity

    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            //Logo
            Image(
                painter = painterResource(id = R.drawable.camusat_logo),
                contentDescription = "Camusat logo",
                modifier = Modifier
                    .width(150.dp)
                    .height(70.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                IconButton(onClick = {
                    isProfileDropDown = true
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.person_icon),
                        contentDescription = "person Icon",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(25.dp)
                    )
                }
                IconButton(onClick = {
                    navController.navigate(Screen.Notifications.route) {
                        navController.popBackStack()
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification_on_icon),
                        contentDescription = "Language Icon",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(25.dp)

                    )
                }
            }
        }
        if (isProfileDropDown) {
            ProfileDialog(
                isExpanded = isProfileDropDown,
                actionCloseDialog = { isProfileDropDown = false },
                actionProfile = {
                    navController.navigate(Screen.Profile.route) {
                        navController.popBackStack()
                    }
                },
                actionLogOut = {
                    scope.launch {
                        sharedPreferenceRepository.setString(key = Constants.isLoggedIn, value = "false", activity = activity)
                        navController.navigate("auth") {
                            popUpTo("auth") { inclusive = false }
                        }
                    }

                }
            )
        }
    }

}

@Composable
fun ProfileDialog(
    isExpanded:Boolean,
    actionCloseDialog:()->Unit,
    actionProfile:() -> Unit,
    actionLogOut:() -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        DropdownMenu(
            onDismissRequest = actionCloseDialog,
            offset = DpOffset(x = 250.dp, y = 0.dp),
            expanded = isExpanded,
            modifier = Modifier.background(color = Color.White)
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Profile",
                        color = Color.DarkGray,
                        fontSize = 14.sp
                    )
                },
                onClick = actionProfile
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = "LogOut",
                        color = Color.DarkGray,
                        fontSize = 14.sp
                    )
                },
                onClick = actionLogOut
            )
        }
    }
}