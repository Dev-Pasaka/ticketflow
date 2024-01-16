package com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.componets

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.commonComponents.SelectDate
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun ScheduleInstallationItem(ticketInformationViewModel: TicketInformationViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                readOnly = true,
                placeholder = {
                    Text(
                        text = "Select Date",
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                    )
                },
                value = ticketInformationViewModel.formatDate(),
                textStyle = TextStyle(
                    fontSize = 12.sp
                ),
                onValueChange = {
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            ticketInformationViewModel.openOrCloseCalender()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription ="Select Date" ,
                            tint = colorResource(id = R.color.button_color)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
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
                    .width(180.dp)
                    .height(50.dp)
            )
            OutlinedTextField(
                readOnly = true,
                placeholder = {
                    Text(
                        text = "Select Time",
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                    )
                },
                value = ticketInformationViewModel.time,
                textStyle = TextStyle(
                    fontSize = 12.sp
                ),
                onValueChange = {
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            ticketInformationViewModel.openOrCloseTimePicker()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.clock_outline),
                            contentDescription ="Select Date" ,
                            tint = colorResource(id = R.color.button_color)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
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
                    .width(180.dp)
                    .height(50.dp)

            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Comment",
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                )
            },
            value = ticketInformationViewModel.scheduleInstallationComment,
            textStyle = TextStyle(
                fontSize = 12.sp
            ),
            onValueChange = {
                            ticketInformationViewModel.updateScheduleInstallationComment(comment = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
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
                text = "Submit Installation Schedule",
            )
        }
        if (ticketInformationViewModel.isTimePickerOpen){
            SelectDate(ticketInformationViewModel = ticketInformationViewModel)
        }
    }
}