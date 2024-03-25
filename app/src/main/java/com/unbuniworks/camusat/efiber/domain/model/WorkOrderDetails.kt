package com.unbuniworks.camusat.efiber.domain.model

data class WorkOrderDetails(
    val id:String,
    val name:String,
    val ticketNo:String,
    val type:String,
    val address:String,
    val client:String,
    val contact:String,
    val equipment:String,
    val status:String,
    val statusColor: String
)