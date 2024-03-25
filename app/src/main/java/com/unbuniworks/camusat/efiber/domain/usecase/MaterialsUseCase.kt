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
){
    fun getMaterials(): Flow<Resource<List<Material>>> = flow {

        try {
            //emit(Resource.Loading(message = "Loading", data = emptyList()))
            val response = repository.getMaterials()
            val materials = response.map { it.toMaterial() }
            emit(Resource.Success(data = materials, message = "Success"))
        }catch (e:Exception){
            e.localizedMessage?.let { Log.e("ClientMaterials", it) }
            emit(Resource.Error( "An expected Error Occurred", emptyList()))
        }
    }
}

suspend fun main(){
  MaterialsUseCase().getMaterials().collect{
      println("Message: ${it.message} Data: ${it.data}")
  }
}

fun cryptoPrices():Flow<Double> = flow {
    while (true){
        delay(1000)
        emit((1..1000000).random().toDouble())
    }


}

