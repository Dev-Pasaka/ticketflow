package com.unbuniworks.camusat.efiber.data.repository

import android.app.Activity
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.WorkOrderDto
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto.WorkOrdersDtoItem
import com.unbuniworks.camusat.efiber.domain.repository.WorkOrdersRepository
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders


class WorkOrdersRepositoryImpl(
    private val api:HttpClient = HttpClient,
):WorkOrdersRepository {

    override suspend fun getWorkOrders(): List<WorkOrdersDtoItem>{

        return api.client.get("${api.baseUrl}workorders/scheduled"){
            header(HttpHeaders.Authorization, "Bearer ")
        }.body<List<WorkOrdersDtoItem>>()

    }

    override suspend fun getWorkOrder(workOrderId: String, activity:Activity): WorkOrderDto {
        return api.client.get("${api.baseUrl}workorders/workorder/$workOrderId"){
            header(HttpHeaders.Authorization, "Bearer ")
        }.body<WorkOrderDto>()

    }

}

suspend fun main(){
    val api:HttpClient = HttpClient

    val workOrderId = "eabaaade-f0d4-4419-918d-c8f5312d67b6"
    println(
        api.client.get("${api.baseUrl}workorders/workorder/$workOrderId"){
            header(HttpHeaders.Authorization, "Bearer ")
        }.body<WorkOrderDto>()
    )
}

