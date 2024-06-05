package com.unbuniworks.camusat.efiber.data.remote.dto.scheduledWorkOrders


import com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto.WorkOrdersDtoItem
import com.unbuniworks.camusat.efiber.domain.model.ScheduledWorkOrders
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduledWorkOrdersDtoItem(
    @SerialName("actionDate")
    val actionDate: String?,
    @SerialName("channel")
    val channel: String,
    @SerialName("column1")
    val column1: String,
    @SerialName("column2")
    val column2: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("deletedAt")
    val deletedAt: String?,
    @SerialName("emailConfigurationsId")
    val emailConfigurationsId: String,
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
    @SerialName("no")
    val no: Int,
    @SerialName("Project")
    val project: Project,
    @SerialName("projectId")
    val projectId: String,
    @SerialName("rawData")
    val rawData: String?,
    @SerialName("scheduledEndAt")
    val scheduledEndAt: String,
    @SerialName("scheduledStartAt")
    val scheduledStartAt: String,
    @SerialName("status")
    val status: String,
    @SerialName("statusColour")
    val statusColour: String,
    @SerialName("team")
    val team: Team,
    @SerialName("teamId")
    val teamId: String,
    @SerialName("type")
    val type: String?,
    @SerialName("updatedAt")
    val updatedAt: String,
    @SerialName("workOrderTasks")
    val workOrderTasks: List<WorkOrderTask> = emptyList(),
    @SerialName("workOrderTeamStatus")
    val workOrderTeamStatus: String
)

fun ScheduledWorkOrdersDtoItem.toScheduledWorkOrder(index:Int): ScheduledWorkOrders {

    return ScheduledWorkOrders(
        id = id,
        ticketId= mainId,
        dueDate = scheduledStartAt ?: "",
    )

}