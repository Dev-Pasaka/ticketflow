package com.unbuniworks.camusat.efiber.data.remote.dto

import android.graphics.Bitmap
import android.net.Uri
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class PostWorkOrderTaskDto(
    val statusColor:String?,
    val isSpecialFeature:Boolean,
    val featureName:String?,
    val workOrderId: String,
    val taskId:String,
    val  userId:String,
    val buttonName:String,
    val features:List<Feature>

)