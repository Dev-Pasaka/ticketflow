package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.TicketsScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.model.WorkOrder
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TicketsScreenLowerSection(
    ticketsScreenViewModel: TicketsScreenViewModel,
    navController:NavHostController,
) {
    TicketsScreenUpperSection(ticketsScreenViewModel = ticketsScreenViewModel)
    if(ticketsScreenViewModel.workOrderState.data?.isEmpty() == true){

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {

            Spacer(modifier = Modifier.height(48.dp))
            Image(
                painter = painterResource(id = R.drawable.icon_new_work_orders),
                contentDescription ="No ticket",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            )
            Text(
                text = "You have no new Tickets",
                color = colorResource(id = R.color.button_color),
                fontSize = 14.sp
            )

        }

    }
    else{
        LazyColumn{
            items(count = ticketsScreenViewModel.workOrderState.data?.size ?: 0){
                val wordOrderId = ticketsScreenViewModel.workOrderState.data?.get(it)?.id
                WorkOrderItem(
                    index = it,
                    ticketsScreenViewModel =ticketsScreenViewModel,
                    actionNavigateToTicketInformationScreen = {
                        navController.currentBackStackEntry?.arguments?.putString(
                            "workOrderId",
                            wordOrderId
                            )
                        navController.navigate(route = "ticket_information")
                    }
                )
            }
        }
    }
}