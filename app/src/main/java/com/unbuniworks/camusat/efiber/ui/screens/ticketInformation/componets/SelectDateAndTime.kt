package com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.TicketInformationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDateAndTime(ticketInformationViewModel: TicketInformationViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val datePickerState =
            rememberDatePickerState(initialSelectedDateMillis = ticketInformationViewModel.selectedDate)
        DatePickerDialog(
            onDismissRequest = {
                ticketInformationViewModel.openOrCloseCalender()
            },
            confirmButton = {
                ticketInformationViewModel.selectDate(
                    date = datePickerState.selectedDateMillis ?: 0L
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