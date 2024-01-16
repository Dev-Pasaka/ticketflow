package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets.TicketsScreenViewModel
import kotlin.random.Random

@Composable
fun WorkOrderItem(
    index: Int,
    ticketsScreenViewModel: TicketsScreenViewModel,
    actionNavigateToTicketInformationScreen: () -> Unit
) {
    val item = ticketsScreenViewModel.workOrder[index]
    val containerColor = if (index == 0){
        R.color.ticket_green
    }else if (index == 1){
        R.color.ticket_green
    }else if (index == 2){
        R.color.ticket_orange
    }else if(index == 3){
        R.color.ticket_maroon
    }else{
        R.color.ticket_maroon
    }
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
                    text = item.id,
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
                Text(
                    text = " - ",
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
                Text(
                    text = item.name,
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )

            }

            Button(
                onClick = actionNavigateToTicketInformationScreen,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = containerColor),
                    contentColor = Color.White
                ),
                modifier = Modifier.height(30.dp)
            ) {
                Text(
                    text = if (index == 0){
                        "Open"
                    }else if (index == 1){
                        "Open"
                    }else if (index == 2){
                        "Scheduled"
                    }else if(index == 3){
                       "Problem"
                    }else{
                       "Problem"
                    },
                    fontSize = 11.sp
                )
            }
        }
        
    }
}

fun generateRandomColor(): Color {
    val random = Random.Default
    val red: Int
    val green: Int
    val blue: Int

    // Generate shades of yellow, lime green, and maroon
    when (random.nextInt(3)) {
        0 -> {
            // Shades of yellow
            red = 255
            green = random.nextInt(256)
            blue = random.nextInt(256)
        }
        1 -> {
            // Shades of lime green
            red = random.nextInt(256)
            green = 255
            blue = random.nextInt(256)
        }
        else -> {
            // Shades of maroon
            red = 128 + random.nextInt(128)
            green = random.nextInt(256)
            blue = random.nextInt(256)
        }
    }

    return Color(android.graphics.Color.rgb(red, green, blue))
}