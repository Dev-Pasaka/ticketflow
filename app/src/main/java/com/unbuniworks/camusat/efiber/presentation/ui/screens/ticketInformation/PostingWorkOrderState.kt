package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation

import kotlinx.serialization.Serializable

@Serializable
data class PostingWorkOrderState(
    val isLoading:Boolean = false,
    val isSuccessFul:Boolean = false,
    val message:String = ""
)
