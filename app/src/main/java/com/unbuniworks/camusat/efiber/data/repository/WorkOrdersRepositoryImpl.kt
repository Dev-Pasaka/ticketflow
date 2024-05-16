package com.unbuniworks.camusat.efiber.data.repository

import android.app.Activity
import androidx.compose.runtime.DisposableEffect
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WorkOrdersRepositoryImpl(
    private val api:HttpClient = HttpClient,
):WorkOrdersRepository {

    override suspend fun getWorkOrders(token:String): List<WorkOrdersDtoItem> {
        return api.client.get("${api.baseUrl}workorders/team"){
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body<List<WorkOrdersDtoItem>>()
    }

    override suspend fun getWorkOrder(workOrderId: String, activity:Activity, token: String): WorkOrderDto {
        return api.client.get("${api.baseUrl}workorders/workorder/$workOrderId"){
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body<WorkOrderDto>()

    }

}

suspend fun main(){
    println(
        WorkOrdersRepositoryImpl().getWorkOrders("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1ZDZkY2QzOC05MDJlLTRkZDgtYjQ4MS1iNjI1MTIzNWFkNjEiLCJlbWFpbCI6InNueWFnd2Fzd2FAZ21haWwuY29tIiwiaWF0IjoxNzEzNDY2Mjc3LCJleHAiOjE3MTM0NjkyNzd9.7LGw284xA5KxPJysvZQI99noa85rxYLng7jbzNS_dSs")
    )
}



