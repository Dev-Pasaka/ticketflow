package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.componets

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
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun ServiceTypeItem(
    index: Int,
    ticketInformationViewModel: TicketInformationViewModel,
) {
    Column {

        Surface(
            onClick = {
                      ticketInformationViewModel.selectTemplate(name =  "")
            },
            color = colorResource(id = R.color.light_gray),
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
                    text = "${index + 1}",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Normal
                )
                if (true){
                    IconButton(
                        onClick = {
                            ticketInformationViewModel.selectTemplate(name = "")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = "Expand",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .size(20.dp)

                        )
                    }
                }else{
                    IconButton(
                        onClick = {
                            ticketInformationViewModel.selectTemplate(name = "")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowForwardIos,
                            contentDescription = "Expand",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .size(20.dp)

                        )
                    }
                }
            }
        }

    }


}
