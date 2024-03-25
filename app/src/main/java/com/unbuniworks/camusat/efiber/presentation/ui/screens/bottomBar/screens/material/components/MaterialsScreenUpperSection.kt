package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
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

fun MaterialsScreenUpperSection() {
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
            Surface(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(5.dp),
                color = colorResource(id = R.color.light_blue),

            ) {
                Text(
                    text = "Request Materials",
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
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