package com.unbuniworks.camusat.efiber.domain.repository

import android.app.Activity
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.WorkOrderDto
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto.WorkOrdersDtoItem

interface WorkOrdersRepository {
    suspend fun getWorkOrders(token:String):List<WorkOrdersDtoItem>
    suspend fun getWorkOrder(workOrderId:String,activity:Activity, token: String):WorkOrderDto
}