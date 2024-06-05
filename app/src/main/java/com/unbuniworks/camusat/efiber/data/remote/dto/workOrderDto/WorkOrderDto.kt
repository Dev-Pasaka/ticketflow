package com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto


import com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto.ActivityLog
import com.unbuniworks.camusat.efiber.domain.model.WorkOrderDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkOrderDto(
    val actionDate: String?,
    val channel: String,
    val createdAt: String,
    val `data`: List<Data>?,
    val deletedAt: String?,
    val emailConfigurationsId: String,
    val emailsIncomingId: String,
    val id: String,
    val mainId: String,
    val manager: Manager? = null,
    val managerId: String? = null,
    @SerialName("Project")
    val project: Project?,
    val projectId: String?,
    val rawData: String,
    val scheduledEndAt: String?,
    val scheduledStartAt: String?,
    val status: String,
    val statusColour: String,
    @SerialName("team")
    val team: Team? = null,
    val teamId: String?,
    val updatedAt: String,
    val workOrderTasks: List<WorkOrderTask>,
    val workOrderTeamStatus: String?,
    val ticketDetails:List<TicketDetail>,
    val workOrderActivityLogs:List<ActivityLog>?
)

fun WorkOrderDto.toWorkOrderDetails():WorkOrderDetails{

    return WorkOrderDetails(
        id = id,
        name = project?.name ?: "",
        ticketNo = mainId,
        status = status,
        statusColor = statusColour,
        workOrderTasks = workOrderTasks,
        ticketDetails = ticketDetails,
        dueDate = scheduledStartAt
    )

}