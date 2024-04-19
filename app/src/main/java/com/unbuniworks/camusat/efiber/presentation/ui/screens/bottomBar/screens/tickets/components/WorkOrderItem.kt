package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.TicketsScreenViewModel

@Composable
fun WorkOrderItem(
    index: Int,
    ticketsScreenViewModel: TicketsScreenViewModel,
    actionNavigateToTicketInformationScreen: () -> Unit
) {
    val item = ticketsScreenViewModel.workOrderState.data?.get(index)

    Surface(
        onClick = actionNavigateToTicketInformationScreen,
        color = Color.White,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp),
    ) {
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = "${index + 1}. ",
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
                Text(
                    text = item?.ticketid ?: "",
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
                Text(
                    text = "  ",
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
                Text(
                    text = item?.name ?: "",
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )

            }

            if (item != null) {
                Surface(
                    onClick = actionNavigateToTicketInformationScreen,
                    color =  ticketsScreenViewModel.getColor(item.statusColor),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier.height(30.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = item.status,
                            fontSize = 11.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
        
    }
}

