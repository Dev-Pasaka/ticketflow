package com.unbuniworks.camusat.efiber.data.remote

import android.app.Application
import com.unbuniworks.camusat.efiber.common.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.io.File

suspend fun main(){
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true // Ignore unknown keys in JSON
            })
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }

        engine {
            // Set timeout
            requestTimeout = 10000 // 5000 milliseconds = 5 seconds

        }
    }
    val baseUrl = "https://api.pdf.co/pdf/convert/to/json-meta"
    val token = "dev.pasaka@gmail.com_3tB5SwS7iB3y6SH61gW88hSjNjj3juDj6gRhUbTAExjLv29W6s09Y2x59Ihqps7R"
    val request =  client.post(baseUrl) {

        contentType(ContentType.Application.Json)
        header("x-api-key",token)
        setBody(
            mapOf(
                "url" to "https://drive.google.com/file/d/1Y9e4238w7ja75MW9KPCAQgDNKG5HCrcF/view")
        ).toString()
    }
    println(request.bodyAsText())
}