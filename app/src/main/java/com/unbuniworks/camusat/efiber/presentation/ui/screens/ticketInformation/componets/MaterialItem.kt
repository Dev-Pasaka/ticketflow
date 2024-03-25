package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.componets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel
import androidx.compose.ui.text.input.ImeAction.*
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MaterialItem(ticketInformationViewModel: TicketInformationViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.light_gray))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Pigtail",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Available Qty: 15",
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                    )
                }

                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = "Qty",
                            fontSize = 10.sp,

                        )
                    },
                    value = ticketInformationViewModel.pigTailQty,
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                    ),
                    onValueChange = {
                        ticketInformationViewModel.updatePigTailQty(newPigTailQty = it)
                    },

                    keyboardOptions = KeyboardOptions(
                        imeAction = Companion.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        cursorColor = Color.DarkGray,
                        focusedIndicatorColor = colorResource(id = R.color.button_color),
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp)

                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Surface(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.light_gray))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Router zte f660",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Available Qty: -7",
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                    )
                }

                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = "Qty",
                            fontSize = 12.sp,
                            color = Color.DarkGray,
                        )
                    },
                    value = ticketInformationViewModel.routeQty,
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    ),
                    onValueChange = {
                        ticketInformationViewModel.updateRouteQty(newRouterQty = it)
                    },

                    keyboardOptions = KeyboardOptions(
                        imeAction = Companion.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        cursorColor = Color.DarkGray,
                        focusedIndicatorColor = colorResource(id = R.color.button_color),
                    ),
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp)
                )

            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Comments",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                )
            },
            value = ticketInformationViewModel.materialComments,
            textStyle = TextStyle(
                fontSize = 14.sp
            ),
            onValueChange = {
                ticketInformationViewModel.updateMaterialComments(it)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = Companion.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {

                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                cursorColor = Color.DarkGray,
                focusedIndicatorColor = colorResource(id = R.color.button_color),
            ),
            modifier = Modifier
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.button_color),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth(),

            ) {
            Text(
                text = "Submit Material",
            )
        }
    }
}