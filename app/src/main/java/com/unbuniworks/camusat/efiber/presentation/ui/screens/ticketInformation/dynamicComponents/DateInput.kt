package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

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
import androidx.compose.material3.rememberDatePickerState
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
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    feature: Feature,
    index: Int,
    ticketInformationViewModel: TicketInformationViewModel,
    status: String,
    key:String
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)


    OutlinedTextField(
        readOnly = true,
        placeholder = {
            Text(
                text = "",
                fontSize = 12.sp,
                color = Color.DarkGray,
            )
        },
        value = ticketInformationViewModel.formatDate(selectedDate = feature.value?.toLong() ?: 0L),
        textStyle = TextStyle(
            fontSize = 12.sp
        ),
        onValueChange = {
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    ticketInformationViewModel.openOrCloseDatePicker()
                    ticketInformationViewModel.selectDate(key = key)

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar),
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

    if (ticketInformationViewModel.isDatePickerOpen && ticketInformationViewModel.currentDate == key) {
        Dialog(
            onDismissRequest = {
                ticketInformationViewModel.updateDateComponent(
                    index = index,
                    date = datePickerState.selectedDateMillis.toString(),
                )

                ticketInformationViewModel.openOrCloseDatePicker()
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            ElevatedCard {
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

        }
    }
}