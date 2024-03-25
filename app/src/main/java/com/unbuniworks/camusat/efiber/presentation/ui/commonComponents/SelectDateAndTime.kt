package com.unbuniworks.camusat.efiber.presentation.ui.commonComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDateAndTime(
    index:Int,
    selectedIndex:Int,
    ticketInformationViewModel: TicketInformationViewModel
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
    ) {
        val datePickerState =
            rememberDatePickerState(initialSelectedDateMillis = ticketInformationViewModel.selectedDate)
        DatePickerDialog(
            onDismissRequest = {
                ticketInformationViewModel.openOrCloseCalender()
            },
            confirmButton = {
               val date =  (
                    datePickerState.selectedDateMillis ?: 0L
                )

            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = colorResource(id = R.color.light_blue),
                )
            )
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    index:Int,
    selectedIndex:Int,
    ticketInformationViewModel: TicketInformationViewModel
){
    val state = rememberTimePickerState()
    Dialog(
        onDismissRequest = {ticketInformationViewModel.openOrCloseTimePicker() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = colorResource(id = R.color.background))
        ) {
            TimePicker(
                state = state,
                modifier = Modifier
                    .padding(16.dp)

            )
        }

    }

}