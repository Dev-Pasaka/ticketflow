package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.BottomNavigationViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.BottomAppBar
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.NavDrawerContent
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.TopAppBar
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.components.TicketsScreenLowerSection
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TicketsScreen(
    bottomNavigationViewModel: BottomNavigationViewModel,
    ticketsScreenViewModel: TicketsScreenViewModel,
    navController: NavHostController,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    Scaffold(
        bottomBar = {
            BottomAppBar(
                bottomNavigationViewModel = bottomNavigationViewModel,
                navController = navController
            )
        },
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

    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            if(ticketsScreenViewModel.workOrderState.isLoading){
                Dialog(
                    onDismissRequest = { }
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
            }else {
                TicketsScreenLowerSection(
                    ticketsScreenViewModel = ticketsScreenViewModel,
                    navController = navController
                )
            }

        }
    }
}