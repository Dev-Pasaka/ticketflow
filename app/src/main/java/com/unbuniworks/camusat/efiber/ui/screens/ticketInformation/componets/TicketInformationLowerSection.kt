package com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.componets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.TicketInformationViewModel
import com.unbuniworks.camusat.efiber.ui.commonComponents.SelectDateAndTime

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