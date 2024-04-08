package com.unbuniworks.camusat.efiber.data.repository

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.net.toUri
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderTaskDto
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderResponseDto
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.WorkOrderDto
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import com.unbuniworks.camusat.efiber.domain.repository.WorkOrderRepository
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.ByteArrayOutputStream
import java.io.File

class WorkOrderRepositoryImpl(
    private val api:HttpClient = HttpClient
):WorkOrderRepository {
    override suspend fun getWorkOrder(workOrderId:String)
    : WorkOrderDto  = api.client.get(
        urlString = "${api.baseUrl}workorders/workorder/$workOrderId"
    ).body<WorkOrderDto>()

    override suspend fun postWorkOrderTask(postWorkOrderTaskDto: PostWorkOrderTaskDto, activity: Activity): PostWorkOrderResponseDto {
        val jsonFeatures = Json.encodeToString<List<Feature>>(postWorkOrderTaskDto.features)



        return api.client.submitFormWithBinaryData(
            url = "${api.baseUrl}workorders/submitTask",
            formData = formData {
                append("workOrderId", postWorkOrderTaskDto.workOrderId)
                append("taskId", postWorkOrderTaskDto.taskId)
                postWorkOrderTaskDto.features.filter { it.inputType == "Image" }.forEach {

                    val inputStream = it.value?.toUri()
                        ?.let { it1 -> activity.contentResolver.openInputStream(it1) }

                    if(inputStream != null){
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    val stream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    val bitmapByteArray = stream.toByteArray()


                        append(it.name, bitmapByteArray, Headers.build {
                            append(HttpHeaders.ContentType, "image/png")
                            append(HttpHeaders.ContentDisposition, "filename=\"${it.name}.png\"")
                        })
                    }
                }
                append("features", jsonFeatures)
            }
        ).body<PostWorkOrderResponseDto>()

    }

}

suspend fun main(){
    println(
        WorkOrderRepositoryImpl().getWorkOrder("69afe0b5-316a-4d6c-afab-d7773b3dfbfc")
    )
}