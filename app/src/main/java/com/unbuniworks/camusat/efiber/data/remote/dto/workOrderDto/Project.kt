package com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto


import com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto.TypeName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
   // @SerialName("types")
  //  val types: List<TypeName>?
    @SerialName("EmailTemplates")
    val emailTemplates: List<EmailTemplates>
)