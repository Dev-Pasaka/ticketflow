package com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.componets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
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
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun ServiceTypeItem(
    index:Int,
    ticketInformationViewModel: TicketInformationViewModel,
    content: @Composable () -> Unit
) {
    val item = ticketInformationViewModel.listOfServices[index]
    Column {

        Surface(
            onClick = { ticketInformationViewModel.selectService(name = item) },
            color = if (ticketInformationViewModel.selectedService == item)
                colorResource(id = R.color.light_blue) else colorResource(id = R.color.light_gray),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)

            ) {
                Text(
                    text = "${index + 1}. $item",
                    fontSize = 16.sp,
                    color = if (ticketInformationViewModel.selectedService == item)
                        colorResource(id = R.color.button_color) else Color.DarkGray,
                    fontWeight = if (ticketInformationViewModel.selectedService == item)
                        FontWeight.Bold else FontWeight.Normal
                )
                IconButton(onClick = {
                    ticketInformationViewModel.selectService(name = item)
                }) {
                    if (ticketInformationViewModel.selectedService == item) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = "Expand",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .size(20.dp)

                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.ArrowForwardIos,
                            contentDescription = "Collapse",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .size(20.dp)

                        )
                    }
                }
            }

        }
        if (item == "Schedule Survey"){
            content()
        }
    }
}