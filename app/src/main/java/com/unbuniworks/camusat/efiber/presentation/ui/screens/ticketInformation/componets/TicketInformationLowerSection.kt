package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.componets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.CustomButton
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.CustomDropDown
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.CustomImageUpload
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.CustomNumberInput
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.CustomTextFiled
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.CustomTimeInput
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.DateInput
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.GPSLocation

@Composable
fun TicketInformationLowerSection(
    navController: NavHostController,
    ticketInformationViewModel: TicketInformationViewModel,
    scrollState: LazyListState
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = scrollState
    ) {

        items(count = 0) {
            ServiceTypeItem(index = it, ticketInformationViewModel = ticketInformationViewModel)

        }
    }


}