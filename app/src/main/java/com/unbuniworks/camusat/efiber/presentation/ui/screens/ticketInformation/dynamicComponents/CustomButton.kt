package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.unbuniworks.camusat.efiber.R

@Composable
fun CustomButton(buttonName: String, action: () -> Unit, onCancel: () -> Unit, status: String) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp)

    ){
        OutlinedButton(
            onClick = onCancel,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.light_gray),
                contentColor = Color.DarkGray
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth(0.3f),
        ) {
            Text(
                text = "Cancel",
            )

        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            enabled = true,
            onClick = {
                action()
                Log.e("Submit", status)
                      },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_color),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth(),

            ) {
            Text(
                text = buttonName,
            )
        }
    }




}