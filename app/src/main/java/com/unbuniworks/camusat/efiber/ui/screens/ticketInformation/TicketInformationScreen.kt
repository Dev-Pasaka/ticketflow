package com.unbuniworks.camusat.efiber.ui.screens.ticketInformation

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.commonComponents.TopAppBar
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.componets.TicketInformationLowerSection
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.componets.TicketInformationMiddleScreen
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.componets.TicketInformationUpperSection
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TicketInformationScreen(navController: NavHostController, ticketInformationViewModel: TicketInformationViewModel) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500
        ), label = ""
    )


    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(500)

    }
    Scaffold(
        topBar = {
            TopAppBar()

        },
        containerColor = colorResource(id = R.color.background),
        modifier = Modifier
            .fillMaxSize()
            .alpha(alphaAnim.value)
        ,


        ) {
        Column {
            TicketInformationUpperSection(){navController.popBackStack()}
            TicketInformationMiddleScreen(ticketInformationViewModel =ticketInformationViewModel)
            TicketInformationLowerSection(ticketInformationViewModel =ticketInformationViewModel )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TicketInformationPreview(){
    TicketInformationScreen(navController = rememberNavController(), ticketInformationViewModel = viewModel())
}