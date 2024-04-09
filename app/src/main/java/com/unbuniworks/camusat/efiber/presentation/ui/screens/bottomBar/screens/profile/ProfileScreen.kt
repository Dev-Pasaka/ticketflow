package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.NavDrawerContent
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.TopAppBar
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.components.ProfileScreenBodySection
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.components.ProfileScreenUpperSection
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController, profileScreenViewModel: ProfileScreenViewModel) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit){
        profileScreenViewModel.getUser(context = context)
    }

    if (profileScreenViewModel.getUserDataState.isLoading){
        Dialog(
            onDismissRequest = { /*TODO*/ }
        ) {

            Surface(
                color = Color.White,
                shape = RoundedCornerShape(4.dp)
            ) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.button_color),
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Butt,
                    modifier = Modifier.padding(16.dp)
                )
            }

        }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            NavDrawerContent(navController = navController)
        }
    ) {


        Scaffold(
            topBar = {
                TopAppBar(
                    navController = navController,
                    actionOpenNavDrawer = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            },
            modifier = Modifier.fillMaxSize(),
            containerColor = colorResource(id = R.color.background)
        )
        {
            Column {
                Spacer(modifier = Modifier.height(48.dp))
                ProfileScreenUpperSection(navController = navController)
                ProfileScreenBodySection(profileScreenViewModel = profileScreenViewModel)
                Button(
                    enabled = false,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.light_blue),
                        contentColor = Color.DarkGray
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    onClick = { /*TODO*/ }
                ) {

                    Text(
                        text = "Safety Passport",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}