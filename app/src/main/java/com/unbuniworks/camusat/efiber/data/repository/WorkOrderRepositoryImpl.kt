package com.unbuniworks.camusat.efiber.data.repository

import android.app.Activity
import android.content.ContentResolver
import android.content.UriPermission
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.core.net.toUri
import com.unbuniworks.camusat.efiber.common.Constants
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
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import kotlin.io.path.toPath

class WorkOrderRepositoryImpl(
    private val api: HttpClient = HttpClient
) : WorkOrderRepository {


    override suspend fun getWorkOrder(workOrderId: String, token: String)
            : WorkOrderDto = api.client.get(
        urlString = "${api.baseUrl}workorders/workorder/$workOrderId"
    ) {
        header(HttpHeaders.Authorization, "Bearer $token")
    }.body<WorkOrderDto>()


    override suspend fun postWorkOrderTask(
        postWorkOrderTaskDto: PostWorkOrderTaskDto,
        activity: Activity,
        token: String
    ): PostWorkOrderResponseDto {
        val jsonFeatures = Json.encodeToString(postWorkOrderTaskDto.features)

        Log.e("statusColor", postWorkOrderTaskDto.toString())
        Log.e("Token", token)
        Log.e("feature", jsonFeatures)

        val result = api.client.post(urlString = "${api.baseUrl}workorders/submitTask") {
            header(HttpHeaders.Authorization, "Bearer $token")
            val body = MultiPartFormDataContent(
                formData {
                    append("statusColor", postWorkOrderTaskDto.statusColor ?: "")
                    append("workOrderId", postWorkOrderTaskDto.workOrderId)
                    append("taskId", postWorkOrderTaskDto.taskId)
                    append("featureName", postWorkOrderTaskDto.featureName ?: "")
                    append("isSpecialFeature", postWorkOrderTaskDto.isSpecialFeature)

                    postWorkOrderTaskDto.features.filter { it.inputType == "Image" }.forEach { feature ->
                        val imageUrl = if (isUrlMatchingFormat(feature.value ?: "")) {
                            "${Constants.baseUrl}uploads${feature.value}"
                        } else {
                            feature.value
                        }

                        val byteArray = imageUrl?.let { url ->
                            if (url.startsWith("http")) {
                                downloadImage(url)
                            } else {
                                val uri = Uri.parse(url)
                                val inputStream = activity.contentResolver.openInputStream(uri)
                                inputStream?.use { stream ->
                                    val bitmap = BitmapFactory.decodeStream(stream)
                                    val outputStream = ByteArrayOutputStream()
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                                    outputStream.toByteArray()
                                }
                            }
                        }

                        byteArray?.let { bytes ->
                            append(feature.name, bytes, Headers.build {
                                append(HttpHeaders.ContentType, "image/png")
                                append(HttpHeaders.ContentDisposition, "filename=\"${feature.name}.png\"")
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

    private fun isUrlMatchingFormat(input: String): Boolean {
        val regex = Regex("^\\/workOrderTaskFiles\\/(?:[a-zA-Z0-9]+)?\\.png$")
        return regex.matches(input)
    }

    private fun downloadImage(url: String): ByteArray?  =
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.inputStream.use { inputStream ->
                val bitmap = BitmapFactory.decodeStream(inputStream)
                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.toByteArray()
            }
        } catch (e: Exception) {
            Log.e("DownloadImage", "Error downloading image: ${e.message}")
            null
        }



}


