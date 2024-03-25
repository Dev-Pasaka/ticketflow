package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.unbuniworks.camusat.efiber.R

@Composable
fun CustomButton() {

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_color),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 2.dp)
                .fillMaxWidth(),

            ) {
            Text(
                text = "",
            )
        }


}