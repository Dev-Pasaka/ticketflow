package com.unbuniworks.camusat.efiber.domain.repository

import com.unbuniworks.camusat.efiber.data.remote.dto.scheduledWorkOrders.ScheduledWorkOrdersDtoItem

interface ScheduledWorkOrdersRepository {
    suspend fun getScheduledWorkOrders(token: String):List<ScheduledWorkOrdersDtoItem>
}