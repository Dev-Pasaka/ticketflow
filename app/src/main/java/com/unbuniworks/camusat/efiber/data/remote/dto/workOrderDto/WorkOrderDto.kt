package com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto


import com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto.WorkOrderTask
import com.unbuniworks.camusat.efiber.domain.model.WorkOrderDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkOrderDto(
    @SerialName("actionDate")
    val actionDate: String?,
    @SerialName("channel")
    val channel: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("deletedAt")
    val deletedAt: String?,
    @SerialName("emailConfigurationsId")
    val emailConfigurationsId: String,
    @SerialName("emailsIncomingId")
    val emailsIncomingId: String,
    @SerialName("id")
    val id: String,
    @SerialName("mainId")
    val mainId: String,
    @SerialName("manager")
    val manager: Manager?,
    @SerialName("managerId")
    val managerId: String?,
    @SerialName("Project")
    val project: Project,
    @SerialName("projectId")
    val projectId: String,
    @SerialName("rawData")
    val rawData: String,
    @SerialName("scheduledEndAt")
    val scheduledEndAt: String?,
    @SerialName("scheduledStartAt")
    val scheduledStartAt: String?,
    @SerialName("status")
    val status: String,
    @SerialName("statusColour")
    val statusColour: String,
    @SerialName("team")
    val team: Team,
    @SerialName("teamId")
    val teamId: String,
    @SerialName("updatedAt")
    val updatedAt: String?,
    @SerialName("workOrderTasks")
    val workOrderTasks: List<WorkOrderTask>?,
    @SerialName("workOrderTeamStatus")
    val workOrderTeamStatus: String?
)

fun WorkOrderDto.toWorkOrder():WorkOrderDetails{
    return WorkOrderDetails(
        id = id,
        name = project.name,
        ticketNo = mainId,
        type = project.type.name,
        address = "",
        client = "",
        contact = "0712345678",
        equipment = "",
        status = status,
        statusColor = statusColour
    )
}
