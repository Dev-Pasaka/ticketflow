package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.util.Log
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.materialDto.MaterialsDtoItem
import com.unbuniworks.camusat.efiber.data.remote.dto.materialDto.toFilterMaterials
import com.unbuniworks.camusat.efiber.data.remote.dto.materialDto.toMaterial
import com.unbuniworks.camusat.efiber.data.repository.MaterialRepositoryImpl
import com.unbuniworks.camusat.efiber.data.repository.WorkOrdersRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.Material
import com.unbuniworks.camusat.efiber.domain.model.MaterialsData
import com.unbuniworks.camusat.efiber.domain.repository.MaterialsRepository
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart


class MaterialsUseCase(
    private val repository: MaterialsRepository = MaterialRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    suspend fun getMaterials(activity: Activity): MaterialsData =
        try {
            val token = sharedPreferenceRepository.getString(Constants.token, activity)?: ""
            val response = repository.getMaterials(token)
            val materials = response.toMaterial()
            MaterialsData(
                materials = materials,
                projects = response.toFilterMaterials()
            )
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.e("ClientMaterials", it) }
            MaterialsData()
        }

}


suspend fun main(){
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI4Yjk0MjZiZi1iMWU3LTQwMDQtYWFjNC05YmRhZTM0NTEzNmMiLCJlbWFpbCI6ImRldi5wYXNha2FAZ21haWwuY29tIiwiaWF0IjoxNzE1MDg0NTMzLCJleHAiOjE3MTUwODc1MzN9.WtAh344d2PQZjYARa9d-3iN7ZuWi-IidZHP286pm4nU"
//    println(
//        WorkOrdersRepositoryImpl().getWorkOrders(token)
//    )
    println(
        MaterialRepositoryImpl().getMaterials(token).toMaterial()
    )
}
