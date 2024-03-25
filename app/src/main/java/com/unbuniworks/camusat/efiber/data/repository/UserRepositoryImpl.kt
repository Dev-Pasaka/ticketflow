package com.unbuniworks.camusat.efiber.data.repository

import com.unbuniworks.camusat.efiber.data.remote.dto.loginDto.LoginDto
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import com.unbuniworks.camusat.efiber.data.remote.model.UserCredentials
import com.unbuniworks.camusat.efiber.domain.repository.UserRepository
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url

class UserRepositoryImpl(
    val httpClient: HttpClient = HttpClient
) : UserRepository {

    override suspend fun login(credentials: UserCredentials): LoginDto {
        return httpClient.client.post {
            url("${httpClient.baseUrl}auth/login/mobile")
            setBody(credentials)

        }.body<LoginDto>()
    }

}

suspend fun main() {
    println(
        UserRepositoryImpl().login(
            credentials = UserCredentials(
                email = "bgithinji5@gmail.com",
                password = "123456"
            )
        )
    )

}