package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Utils
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.TicketsScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationEvents

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TicketsScreenUpperSection(ticketsScreenViewModel: TicketsScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 48.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Tickets",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.button_color)
                )

            Text(
                text = Utils.getCurrentFormattedDayOfWeek(),
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Work Orders",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.button_color)
            )

            IconButton(
                onClick = {
                    ticketsScreenViewModel.refresh()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(id = R.color.button_color),
                    contentColor = Color.White,
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)

            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
            }

        }
        
    }
}