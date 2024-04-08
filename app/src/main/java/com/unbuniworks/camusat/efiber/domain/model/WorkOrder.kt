package com.unbuniworks.camusat.efiber.domain.model

import com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto.WorkOrdersTask
import kotlinx.serialization.Serializable

@Serializable
data class WorkOrder(
    val id:String,
    val ticketid:String,
    val name:String,
    val status:String,
    val statusColor:String,
    val workOrdersTasks: List<WorkOrdersTask>?,
)


