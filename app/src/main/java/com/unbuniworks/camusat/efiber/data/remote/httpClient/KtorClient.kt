package com.unbuniworks.camusat.efiber.data.remote.httpClient

import com.google.gson.Gson
import com.unbuniworks.camusat.efiber.common.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClient {
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
            requestTimeout = (1000*60)*2 // 5000 milliseconds = 5 seconds

        }
    }
    val baseUrl = Constants.baseUrl
}