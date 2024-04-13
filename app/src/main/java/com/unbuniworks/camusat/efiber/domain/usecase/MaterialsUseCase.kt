package com.unbuniworks.camusat.efiber.domain.usecase

import android.util.Log
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.remote.dto.materialDto.MaterialsDtoItem
import com.unbuniworks.camusat.efiber.data.remote.dto.materialDto.toMaterial
import com.unbuniworks.camusat.efiber.data.repository.MaterialRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.Material
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
    private val repository: MaterialsRepository = MaterialRepositoryImpl()
) {
    suspend fun getMaterials(): List<Material> =
        try {
            val response = repository.getMaterials()
            val materials = response.map { it.toMaterial() }
            materials
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.e("ClientMaterials", it) }
            emptyList()
        }

}



