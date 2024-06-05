package com.unbuniworks.camusat.efiber.data.repository

import com.unbuniworks.camusat.efiber.data.remote.dto.SubmitUserLogsDto
import com.unbuniworks.camusat.efiber.data.remote.dto.getUserDto.GetUserDto
import com.unbuniworks.camusat.efiber.data.remote.dto.loginDto.LoginDto
import com.unbuniworks.camusat.efiber.data.remote.dto.resetPasswordDto.ResetPasswordDto
import com.unbuniworks.camusat.efiber.data.remote.dto.updatePasswordDto.UpdatePasswordDto
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import com.unbuniworks.camusat.efiber.data.remote.model.ResetPasswordCredentials
import com.unbuniworks.camusat.efiber.data.remote.model.UserCredentials
import com.unbuniworks.camusat.efiber.domain.repository.UserRepository
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    val httpClient: HttpClient = HttpClient
) : UserRepository {

    override suspend fun login(credentials: UserCredentials): LoginDto = withContext(Dispatchers.IO) {
        return@withContext httpClient.client.post {
            url("${httpClient.baseUrl}auth/login/mobile")
            setBody(credentials)

        }.body<LoginDto>()
    }

    override suspend fun getUserDto(userId:String, token: String): GetUserDto {
        return httpClient.client.get("${httpClient.baseUrl}users/mobile/$userId"){
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body<GetUserDto>()
    }

    override suspend fun resetPassword(resetPasswordCredentials: ResetPasswordCredentials)  = withContext(Dispatchers.Main.immediate){
        return@withContext httpClient.client.post {
            url("${httpClient.baseUrl}auth/reset")
            setBody(resetPasswordCredentials)

        }.body<ResetPasswordDto>()
    }

    override suspend fun updatePassword(newPassword: String, token:String): UpdatePasswordDto  = withContext(Dispatchers.Main.immediate){
        return@withContext httpClient.client.post{
            header(HttpHeaders.Authorization, "Bearer $token")
            url("${httpClient.baseUrl}auth/setpassword")
            setBody(mapOf("password" to newPassword))
        }.body<UpdatePasswordDto>()
    }

    override suspend fun submitUserLogs(logs: SubmitUserLogsDto, token: String): Map<String, Boolean>  = withContext(Dispatchers.IO){
        httpClient.client.post("${httpClient.baseUrl}logs/user"){
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody(logs)
        }.body<Map<String, Boolean>>()
    }

}

suspend fun main() {
  /*  println(
        UserRepositoryImpl().login(
            credentials = UserCredentials(
                email = "dev.pasaka@gmail.com",
                password = "Uh>Fggu(?fPH",
                deviceId = "23021RAAEG_UKQ1.230917.001"
            )
        )

       // UserRepositoryImpl().resetPassword(resetPasswordCredentials = ResetPasswordCredentials(email = "dev.pasaka@gmail.com"))
    )*/

    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJmYmYwZmY1Yi1kNzI4LTQwZmItOWI0Yy00YzYzZWYwZDkzZmMiLCJlbWFpbCI6ImRldi5wYXNha2FAZ21haWwuY29tIiwiaWF0IjoxNzE3NTc2MDE4LCJleHAiOjE3MTc1NzkwMTh9.YPigJTx5DyV7UxGHUC22s5h8WpaFA2RSeey9i9Tt6sk"
    println(
        UserRepositoryImpl().submitUserLogs(
            logs = SubmitUserLogsDto(
                module = "Test",
                activity = "Testing",
                detail = "TEST 0NE"
            ),
            token = token
        )
    )

}


