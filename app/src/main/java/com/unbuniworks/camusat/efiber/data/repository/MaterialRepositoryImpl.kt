package com.unbuniworks.camusat.efiber.data.repository

import android.app.Activity
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.dispatchProjectDto.DispatchProjectDto
import com.unbuniworks.camusat.efiber.data.remote.dto.materialDto.MaterialsDtoItem
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import com.unbuniworks.camusat.efiber.domain.repository.MaterialsRepository
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.*

class MaterialRepositoryImpl(
    private val api:HttpClient = HttpClient
):MaterialsRepository {
    override suspend fun getMaterials(token:String): MaterialsDtoItem {
        return api.client.get("${api.baseUrl}teamMaterial/team"){
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body<MaterialsDtoItem>()
    }

    override suspend fun getDispatchProject(token: String): DispatchProjectDto {
        return api.client.get("${api.baseUrl}dispatchproject/me"){
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body<DispatchProjectDto>()
    }

}

