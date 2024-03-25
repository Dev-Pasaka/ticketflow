package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets

import com.unbuniworks.camusat.efiber.domain.model.WorkOrder


data class WorkOrderState(
    val isLoading:Boolean = false,
    val data:List<WorkOrder> = emptyList(),
    val error:String = ""
)