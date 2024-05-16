package com.unbuniworks.camusat.efiber.data.remote.dto.materialDto


import com.unbuniworks.camusat.efiber.domain.model.Project
import com.unbuniworks.camusat.efiber.domain.model.Material
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaterialsDtoItem(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: String
)

fun MaterialsDtoItem.toMaterial():List<Material>{
    return data.map { material ->
        Material(
            dispatchProjectId  = material.material.dispatchProjectId,
            name = material.material.materialName,
            qty = material.quantityRemaining
        )
    }
}

fun MaterialsDtoItem.toFilterMaterials():List<Project>{
    return data.map { material ->
        Project(
            name = material.dispatchProject.first().name,
            id = material.dispatchProject.first().id
        )
    }
}
