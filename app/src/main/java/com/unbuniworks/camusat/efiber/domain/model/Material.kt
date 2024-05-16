package com.unbuniworks.camusat.efiber.domain.model

data class Material(
    val dispatchProjectId:String,
    val name:String,
    val qty:String
)

data class MaterialsData(
    val materials:List<Material> = emptyList(),
    val projects:List<Project> = emptyList()
)

