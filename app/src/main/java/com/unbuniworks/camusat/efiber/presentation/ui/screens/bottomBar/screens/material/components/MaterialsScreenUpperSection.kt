package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.MaterialScreenViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationEvents

@Composable

fun MaterialsScreenUpperSection(materialScreenViewModel: MaterialScreenViewModel) {
    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 60.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Materials",
                color = colorResource(id = R.color.button_color),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )


            TextButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = colorResource(id = R.color.button_color),
                )
            ) {

                Text(
                    text = "Request Materials",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

            }

            IconButton(
                onClick = {
                    materialScreenViewModel.refresh()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(id = R.color.light_blue),
                    contentColor = Color.White,
                )
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }

        Surface(
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
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "No. Item",
                    fontSize = 14.sp,
                )

                Text(
                    text = "Available Qty",
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
            }

        }
    }

}