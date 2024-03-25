package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.model.ProfileItems

class ProfileScreenViewModel():ViewModel() {

    val profileItems by mutableStateOf(
        listOf(
            ProfileItems(title = "Name", body = "Jose Thomas"),
            ProfileItems(title = "Position", body = "Head of Busimess Group"),
            ProfileItems(title = "Staff ID No.", body = "CM/526"),
            ProfileItems(title = "IMEI No.", body = "56326729301"),
        )
    )

}