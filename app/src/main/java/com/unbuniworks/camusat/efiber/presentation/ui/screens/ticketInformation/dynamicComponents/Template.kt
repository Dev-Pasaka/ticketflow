package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.unbuniworks.camusat.efiber.R

@Composable
fun Template(
    status:String,
    templateName:String,
    isSelected:Boolean,
    containerColor:Color,
    textColor:Color,
    templatePosition:Int,
    onclick:() ->Unit
) {
    Surface(

        onClick = onclick,
        shape = RoundedCornerShape(5.dp),
        color = containerColor,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(2.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Text(
                text = "$templatePosition. $templateName",
                color = textColor,
                fontSize = 14.sp
            )

            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){

                IconButton(onClick = onclick) {
                    if (status == "complete"){
                        Surface(
                            shape = CircleShape,
                            color = colorResource(id = R.color.green),
                            modifier = Modifier.padding(end = 8.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Submitted",
                                tint = Color.White,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }else {
                        when (isSelected) {
                            true -> {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowUp,
                                    contentDescription = "Selected",
                                    tint = Color.DarkGray
                                )
                            }

                            else -> {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Not selected",
                                    tint = Color.DarkGray
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}