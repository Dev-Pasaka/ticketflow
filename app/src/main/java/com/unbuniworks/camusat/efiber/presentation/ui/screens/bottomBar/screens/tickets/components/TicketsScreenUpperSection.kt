package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

@Composable
fun TicketsScreenUpperSection(){
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
                text = "Wednesday 11/10/2023",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Work Orders",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.button_color)
            )

        }
        
    }
}