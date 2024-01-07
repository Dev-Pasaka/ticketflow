package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.tickets.model.WorkOrder

class TicketsScreenViewModel():ViewModel() {
    var workOrder by mutableStateOf(
        mutableListOf(
            WorkOrder(
                id = "564323456",
                name = "Casablanca"
            ),
            WorkOrder(
                id = "5644532156",
                name = "2BIT"
            ),
            WorkOrder(
                id = "983912893",
                name = "Quiver"
            ),
            WorkOrder(
                id = "837454532",
                name = "Garden City"
            ),
            WorkOrder(
                id = "112743442",
                name = "Sarit Center"
            )
        )
    )
}