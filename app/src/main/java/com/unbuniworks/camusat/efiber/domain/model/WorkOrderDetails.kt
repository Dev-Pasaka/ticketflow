package com.unbuniworks.camusat.efiber.domain.model

import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.EmailTemplates
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.TicketDetail
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.WorkOrderTask

data class WorkOrderDetails(
    val id:String,
    val name:String,
    val ticketNo:String,
    val status:String,
    val statusColor: String,
    val ticketDetails:List<TicketDetail>,
    val dueDate:String?,
    val workOrderTasks: List<WorkOrderTask>?,
    val emailTemplates:List<EmailTemplates>

)