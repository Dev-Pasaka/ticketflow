package com.unbuniworks.camusat.efiber.data.remote.dto.dispatchProjectDto


import com.unbuniworks.camusat.efiber.data.remote.dto.materialDto.MaterialsDtoItem
import com.unbuniworks.camusat.efiber.domain.model.Project
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DispatchProjectDto(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: String
)

fun DispatchProjectDto.toFilterMaterials():List<Project>{
    return data.map { project ->
        Project(
            name  = project.name,
            id = project.id
        )
    }
}