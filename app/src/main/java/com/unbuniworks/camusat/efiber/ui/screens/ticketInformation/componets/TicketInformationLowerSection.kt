package com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.KeyboardType
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.TicketInformationViewModel
import androidx.compose.material3.TextFieldDefaults

@Composable
fun TicketInformationLowerSection(ticketInformationViewModel: TicketInformationViewModel) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ){
        items(count = ticketInformationViewModel.listOfServices.size){
            ServiceTypeItem(index = it, ticketInformationViewModel =ticketInformationViewModel )
        }
    }
    
    if (ticketInformationViewModel.isCalenderOpen){
        SelectDateAndTime(ticketInformationViewModel = ticketInformationViewModel)
    }

}