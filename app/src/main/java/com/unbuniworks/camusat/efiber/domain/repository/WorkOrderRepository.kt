package com.unbuniworks.camusat.efiber.domain.repository

import android.app.Activity
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderTaskDto
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderResponseDto
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.WorkOrderDto

interface WorkOrderRepository {
    suspend fun getWorkOrder(workOrderId:String):WorkOrderDto
    suspend fun postWorkOrderTask(postWorkOrderTaskDto: PostWorkOrderTaskDto,activity: Activity): PostWorkOrderResponseDto
}