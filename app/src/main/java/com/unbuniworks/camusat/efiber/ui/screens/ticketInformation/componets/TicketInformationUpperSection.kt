package com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.componets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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

@Composable
fun TicketInformationUpperSection(goBack:() -> Unit) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 48.dp)
    ){

            Surface(

                onClick = goBack,
                color = colorResource(id = R.color.background),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription ="Back Arrow",
                    tint = colorResource(id = R.color.button_color),
                    modifier = Modifier.size(20.dp)

                )
            }


        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 0.dp, vertical = 16.dp)
        ){

            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
            ){
                Text(
                    text = "Ticket No. ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.button_color),

                    )
                Text(
                    text = "623451612",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.button_color),
                    )
            }


        }
        Spacer(modifier = Modifier.width(16.dp))

        OutlinedButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = colorResource(id = R.color.ticket_orange),
            ),
            border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.ticket_orange)),
            shape = RoundedCornerShape(5.dp),

            ) {

            Text(
                text = "Scheduled",
            )

        }
/*
        Surface(
            color = colorResource(id = R.color.background),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowForwardIos,
                contentDescription = "Back Arrow",
                tint = colorResource(id = R.color.button_color),
                modifier = Modifier.size(20.dp)

            )
        }*/


    }
}