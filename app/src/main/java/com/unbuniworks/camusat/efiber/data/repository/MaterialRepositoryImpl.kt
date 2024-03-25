package com.unbuniworks.camusat.efiber.data.repository

import android.app.Activity
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.materialDto.MaterialsDtoItem
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import com.unbuniworks.camusat.efiber.domain.repository.MaterialsRepository
import io.ktor.client.call.body
import io.ktor.client.request.get

class MaterialRepositoryImpl(
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl(),
    private val api:HttpClient = HttpClient
):MaterialsRepository {
    override suspend fun getMaterials(): List<MaterialsDtoItem> {
        return api.client.get("${api.baseUrl}clientMaterial").body<List<MaterialsDtoItem>>()
    }
}

suspend fun main(){
    println(MaterialRepositoryImpl().getMaterials())
}