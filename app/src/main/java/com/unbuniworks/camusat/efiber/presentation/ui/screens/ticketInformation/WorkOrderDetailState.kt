package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation

import com.unbuniworks.camusat.efiber.domain.model.WorkOrder
import com.unbuniworks.camusat.efiber.domain.model.WorkOrderDetails

data class WorkOrderDetailState(
    val isLoading:Boolean = false,
    val data:WorkOrderDetails? = null,
    val error:String = ""
)