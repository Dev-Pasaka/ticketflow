package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.TopAppBar
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.TicketInformationMiddleScreen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.TicketInformationUpperSection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.unbuniworks.camusat.efiber.domain.model.WorkOrder
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.TicketInformationLowerSection


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TicketInformationScreen(navController: NavHostController, ticketInformationViewModel: TicketInformationViewModel, workOrder: String) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500
        ), label = ""
    )

    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(500)
    }

    LaunchedEffect(key1 = Unit){
        scope.launch {
            ticketInformationViewModel.getWorkOrder(workOrder)
        }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


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
            containerColor = colorResource(id = R.color.background),
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaAnim.value),
        ) {
            Column {
                TicketInformationUpperSection(ticketInformationViewModel = ticketInformationViewModel) {
                    navController.popBackStack()
                }
                TicketInformationMiddleScreen(
                    ticketInformationViewModel = ticketInformationViewModel,
                    lazyListState = scrollState
                )
                TicketInformationLowerSection(
                    ticketInformationViewModel = ticketInformationViewModel,
                    navController = navController
                )
            }


            if (ticketInformationViewModel.workOrderDetailState.isLoading) {
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
        }

}

