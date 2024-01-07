package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets.TicketsScreenViewModel

@Composable
fun TicketsScreenLowerSection(ticketsScreenViewModel: TicketsScreenViewModel) {
    TicketsScreenUpperSection()
    if(ticketsScreenViewModel.workOrder.isEmpty()){

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {

            Text(
                text = "You have no new Tickets",
                color = colorResource(id = R.color.button_color),
                fontSize = 14.sp
            )

        }

    }else{
        LazyColumn{
            items(count = ticketsScreenViewModel.workOrder.size){
                WorkOrderItem(index = it, ticketsScreenViewModel =ticketsScreenViewModel )
            }
        }
    }
}