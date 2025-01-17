package com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto


import com.unbuniworks.camusat.efiber.domain.model.ScheduledWorkOrders
import com.unbuniworks.camusat.efiber.domain.model.WorkOrder
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkOrdersDtoItem(
    @SerialName("actionDate")
    val actionDate: String? = null,
    @SerialName("channel")
    val channel: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("data")
    val `data`: List<Data>?,
    @SerialName("deletedAt")
    val deletedAt: String? = null,
    @SerialName("emailConfigurationsId")
    val emailConfigurationsId: String?,
    @SerialName("emailsIncomingId")
    val emailsIncomingId: String?,
    @SerialName("id")
    val id: String,
    @SerialName("mainId")
    val mainId: String,
    @SerialName("manager")
    val manager: Manager?,
    @SerialName("managerId")
    val managerId: String?,
    @SerialName("Project")
    val project: Project?,
    @SerialName("projectId")
    val projectId: String?,
    @SerialName("scheduledEndAt")
    val scheduledEndAt: String? = null,
    @SerialName("dueDate")
    val scheduledStartAt: String? = null,
    @SerialName("status")
    val status: String,
    @SerialName("statusColour")
    val statusColour: String,
    @SerialName("team")
    val team: Team?,
    @SerialName("teamId")
    val teamId: String?,
    @SerialName("type")
    val type: String? = null,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("workOrderTasks")
    val workOrdersTasks: List<WorkOrdersTask>?,
    @SerialName("workOrderTeamStatus")
    val workOrderTeamStatus: String? = null // Nullable field
)


fun WorkOrdersDtoItem.toWorkOrder(): WorkOrder {
    return WorkOrder(
        id = id,
        ticketid = mainId,
        name = "", // Assuming you want to use mainId as the name
        status = status,
        statusColor = statusColour,
        workOrdersTasks = workOrdersTasks,
    )
}






