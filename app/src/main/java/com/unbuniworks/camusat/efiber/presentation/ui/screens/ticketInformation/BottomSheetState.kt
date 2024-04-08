package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation

import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature

data class BottomSheetState(
    val isBottomSheetOpen: Boolean = false,
    val workOrderId :String = "",
    val data: List<Feature> = emptyList()
)