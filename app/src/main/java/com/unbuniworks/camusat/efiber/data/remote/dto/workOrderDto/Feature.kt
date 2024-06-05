package com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto


import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feature(
    @SerialName("classification")
    val classification: String,
    @SerialName("inputType")
    val inputType: String,
    @SerialName("name")
    val name: String,
    @SerialName("options")
    val options: List<String>,
    @SerialName("value")
    var value: String?,
    @SerialName("availableQuantity")
    val availableQuantity:Int? = null,
){
    fun updateValue(newValue: String?) {
        value = newValue
    }
}