package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import com.unbuniworks.camusat.efiber.presentation.ui.commonComponents.TimePicker
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimeInput(
    index:Int,
    feature: Feature,
    ticketInformationViewModel: TicketInformationViewModel,
    key:String
) {
    val timePicker = rememberTimePickerState(is24Hour = true)

    OutlinedTextField(
        readOnly = true,
        placeholder = {
            Text(
                text = "12.00 am",
                fontSize = 12.sp,
                color = Color.DarkGray,
            )
        },
        value = ticketInformationViewModel.convertFromUtc(feature.value ?: "4:30")
        ,
        textStyle = TextStyle(
            fontSize = 12.sp
        ),
        onValueChange = {
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    ticketInformationViewModel.openOrCloseTimePicker()
                    ticketInformationViewModel.selectTime(key = key)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.clock_outline),
                    contentDescription = "Select Date",
                    tint = colorResource(id = R.color.button_color)
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
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
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .height(50.dp)

    )

    if (ticketInformationViewModel.isTimePickerOpen && ticketInformationViewModel.currentTime == key) {
        Dialog(
            onDismissRequest = {
                ticketInformationViewModel.updateTimeComponent(
                    index = index,
                    time = ticketInformationViewModel.convertToUtc("${timePicker.hour}:${timePicker.minute}")
                )
                ticketInformationViewModel.openOrCloseTimePicker()
                Log.e("Time", "${timePicker.hour}:${timePicker.minute}")
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            ElevatedCard {
               androidx.compose.material3.TimePicker(
                   state = timePicker,
                   modifier = Modifier.padding(16.dp)
               )
            }

        }
    }

}