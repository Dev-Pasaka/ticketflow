package com.unbuniworks.camusat.efiber.domain.repository

import android.app.Activity
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderTaskDto
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderResponseDto
import com.unbuniworks.camusat.efiber.data.remote.dto.SubmitEmailTemplateResponseDto
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.WorkOrderDto

interface WorkOrderRepository {
    suspend fun getWorkOrder(workOrderId:String, token:String):WorkOrderDto
    suspend fun postWorkOrderTask(postWorkOrderTaskDto: PostWorkOrderTaskDto,activity: Activity, token: String): PostWorkOrderResponseDto
    suspend fun sendEmailTemplate(workOrderId: String, templateId:String,token: String): SubmitEmailTemplateResponseDto
}