package com.unbuniworks.camusat.efiber.data.repository

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderTaskDto
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderResponseDto
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.WorkOrderDto
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import com.unbuniworks.camusat.efiber.domain.repository.WorkOrderRepository
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.ByteArrayOutputStream
import java.io.File

class WorkOrderRepositoryImpl(
    private val api: HttpClient = HttpClient
) : WorkOrderRepository {
    override suspend fun getWorkOrder(workOrderId: String, token:String)
            : WorkOrderDto = api.client.get(
        urlString = "${api.baseUrl}workorders/workorder/$workOrderId"
    ){
        header(HttpHeaders.Authorization, "Bearer $token")
    }.body<WorkOrderDto>()

    override suspend fun postWorkOrderTask(
        postWorkOrderTaskDto: PostWorkOrderTaskDto,
        activity: Activity,
        token: String
    ): PostWorkOrderResponseDto {
        val jsonFeatures = Json.encodeToString<List<Feature>>(postWorkOrderTaskDto.features)

        Log.e("statusColor", postWorkOrderTaskDto.toString())
        Log.e("Token", token)
        Log.e("feature", jsonFeatures)

        val result = api.client.post(urlString = "${api.baseUrl}workorders/submitTask"){
            header(HttpHeaders.Authorization, "Bearer $token")
            val body =  MultiPartFormDataContent(
                formData {
                    append("statusColor", postWorkOrderTaskDto.statusColor ?: "")
                    append("workOrderId", postWorkOrderTaskDto.workOrderId)
                    append("taskId", postWorkOrderTaskDto.taskId)
                    append("featureName", postWorkOrderTaskDto.featureName ?: "")
                    append("isSpecialFeature", postWorkOrderTaskDto.isSpecialFeature)
                    postWorkOrderTaskDto.features.filter { it.inputType == "Image" }.forEach {

                        val inputStream = it.value?.toUri()
                            ?.let { it1 -> activity.contentResolver.openInputStream(it1) }

                        if (inputStream != null) {
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
            )
            setBody(body)


        }.body<PostWorkOrderResponseDto>()
        Log.e("statusColor", postWorkOrderTaskDto.toString())
        Log.e("Test", result.toString())
        return result
    }

}

suspend fun main() {

    val workOrderId = "69afe0b5-316a-4d6c-afab-d7773b3dfbfc"

   println(
       HttpClient.client.get(
           urlString = "${HttpClient.baseUrl}workorders/workorder/$workOrderId"
       ).body<WorkOrderDto>()
   )


}