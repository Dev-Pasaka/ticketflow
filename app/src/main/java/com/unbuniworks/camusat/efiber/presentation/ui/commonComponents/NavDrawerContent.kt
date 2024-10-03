package com.unbuniworks.camusat.efiber.presentation.ui.commonComponents

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun NavDrawerContent(
    navController: NavHostController,
    sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()

) {
    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity

    var isLogOutDialogOpen by remember {
        mutableStateOf(false)
    }

    if (isLogOutDialogOpen){
        LogOutDialogBox(
            icon = Icons.AutoMirrored.Outlined.Logout,
            dialogTitle = "You are about to logout",
            dialogText = "Are you sure you want to logout?",
            onDismissRequest = { isLogOutDialogOpen = !isLogOutDialogOpen},
            onConfirmation = {
                scope.launch {

                }
            }
        )
    }

    ModalDrawerSheet(
        drawerContainerColor = colorResource(id = R.color.background),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ticket_flow_logo),
            contentDescription = "person Icon",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.7f)
                .height(70.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        NavigationDrawerItem(
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent,
            ),
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Profile"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Profile")
                }
            },
            selected = false,
            onClick = {
                navController.navigate(Screen.Profile.route) {
                    navController.popBackStack()
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        NavigationDrawerItem(
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent,
            ),
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Logout,
                        contentDescription = "LogOut"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "LogOut")
                }
            },
            selected = false,
            onClick = {
                isLogOutDialogOpen = !isLogOutDialogOpen
            }
        )
    }


}


@Composable
fun LogOutDialogBox(
    icon: ImageVector,
    dialogTitle:String,
    dialogText: String,
    onDismissRequest:()-> Unit,
    onConfirmation:()-> Unit


) {
    AlertDialog(
        containerColor = colorResource(id = R.color.background),
        icon = {
            Icon(icon, contentDescription = "Logout icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}