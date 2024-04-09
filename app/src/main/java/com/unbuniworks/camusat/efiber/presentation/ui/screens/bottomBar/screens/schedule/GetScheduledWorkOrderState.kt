package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule

import com.unbuniworks.camusat.efiber.domain.model.ScheduledWorkOrders

data class GetScheduledWorkOrderState(
    val isLoading:Boolean = false,
    val error:String = "",
    val data:List<ScheduledWorkOrders> = emptyList()
)
