package com.unbuniworks.camusat.efiber.data.repository

import com.unbuniworks.camusat.efiber.data.remote.dto.scheduledWorkOrders.ScheduledWorkOrdersDtoItem
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto.WorkOrdersDtoItem
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import com.unbuniworks.camusat.efiber.domain.repository.ScheduledWorkOrdersRepository
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ScheduledWorkOrdersRepositoryImpl(
    private val api: HttpClient = HttpClient,

    ):ScheduledWorkOrdersRepository {
    override suspend fun getScheduledWorkOrders(token: String): List<ScheduledWorkOrdersDtoItem> {
        return api.client.get("${api.baseUrl}workorders/scheduled/team"){
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body<List<ScheduledWorkOrdersDtoItem>>()
    }
}

suspend fun main(){
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJmYmYwZmY1Yi1kNzI4LTQwZmItOWI0Yy00YzYzZWYwZDkzZmMiLCJlbWFpbCI6ImRldi5wYXNha2FAZ21haWwuY29tIiwiaWF0IjoxNzE1ODU5OTYzLCJleHAiOjE3MTU4NjI5NjN9.EZGyMjuW3r3d4u19UNlT2NtJ9u2K7lwcfBwCAxxbu9U"
    println(
        ScheduledWorkOrdersRepositoryImpl().getScheduledWorkOrders(token)
    )
}
