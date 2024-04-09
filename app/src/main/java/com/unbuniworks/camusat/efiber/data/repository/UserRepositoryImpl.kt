package com.unbuniworks.camusat.efiber.data.repository

import com.unbuniworks.camusat.efiber.data.remote.dto.getUserDto.GetUserDto
import com.unbuniworks.camusat.efiber.data.remote.dto.loginDto.LoginDto
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import com.unbuniworks.camusat.efiber.data.remote.model.UserCredentials
import com.unbuniworks.camusat.efiber.domain.repository.UserRepository
import io.ktor.client.call.body
import io.ktor.client.request.*

class UserRepositoryImpl(
    val httpClient: HttpClient = HttpClient
) : UserRepository {

    override suspend fun login(credentials: UserCredentials): LoginDto {
        return httpClient.client.post {
            url("${httpClient.baseUrl}auth/login/mobile")
            setBody(credentials)

        }.body<LoginDto>()
    }

    override suspend fun getUserDto(userId:String): GetUserDto {
        return httpClient.client.get("${httpClient.baseUrl}users/mobile/$userId").body<GetUserDto>()
    }

}

suspend fun main() {
    println(
        UserRepositoryImpl().login(
            credentials = UserCredentials(
                email = "dev.pasaka@gmail.com",
                password = "123456",
                deviceId = "23021RAAEG_UKQ1.230917.001"
            )
        )
    )

}