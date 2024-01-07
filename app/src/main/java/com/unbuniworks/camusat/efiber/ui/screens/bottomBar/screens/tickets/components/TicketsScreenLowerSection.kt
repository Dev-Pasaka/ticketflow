package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets.TicketsScreenViewModel

@Composable
fun TicketsScreenLowerSection(
    ticketsScreenViewModel: TicketsScreenViewModel,
    actionNavigateToTicketInformationScreen: () -> Unit
) {
    TicketsScreenUpperSection()
    if(ticketsScreenViewModel.workOrder.isEmpty()){

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
                modifier = Modifier.width(200.dp)
                    .height(200.dp)
            )
            Text(
                text = "You have no new Tickets",
                color = colorResource(id = R.color.button_color),
                fontSize = 14.sp
            )

        }

    }else{
        LazyColumn{
            items(count = ticketsScreenViewModel.workOrder.size){
                WorkOrderItem(
                    index = it,
                    ticketsScreenViewModel =ticketsScreenViewModel,
                    actionNavigateToTicketInformationScreen = actionNavigateToTicketInformationScreen                    )
            }
        }
    }
}