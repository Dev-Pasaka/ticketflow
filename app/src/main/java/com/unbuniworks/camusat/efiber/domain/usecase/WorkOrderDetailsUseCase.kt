package com.unbuniworks.camusat.efiber.domain.usecase

import android.util.Log
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.toWorkOrderDetails
import com.unbuniworks.camusat.efiber.data.repository.WorkOrderRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.WorkOrderDetails
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WorkOrderDetailsUseCase(
    private val repository: WorkOrderRepositoryImpl = WorkOrderRepositoryImpl()
) {
    fun getWorkOrderDetails(workOrderId: String): Flow<Resource<WorkOrderDetails>> = flow {
        try {
            emit(Resource.Loading( message = "Loading"))
            val result = repository.getWorkOrder(workOrderId = workOrderId).toWorkOrderDetails()
            emit(Resource.Success(data = result, message = "Data fetch was successful"))

        } catch (e:IOException){
            e.localizedMessage?.let { Log.e("WorkOrdersStatus", it) }
            emit(Resource.Error( "Couldn't reach server check your internet connection"))
        }
        catch (e: JsonConvertException){
            e.localizedMessage?.let { Log.e("WorkOrdersStatus", it) }
            emit(Resource.Error( "An expected Error Occurred"))
        }
        catch (e:Exception){
            e.localizedMessage?.let { Log.e("WorkOrdersStatus", it) }
            emit(Resource.Error( "An expected Error Occurred"))
        }

    }
}

suspend fun main(){
    WorkOrderDetailsUseCase().getWorkOrderDetails("").collect{
        println("Message: ${it.message} Data: ${it.data}")
    }
}