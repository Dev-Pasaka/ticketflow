package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.util.Log
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.toWorkOrder
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto.toWorkOrder
import com.unbuniworks.camusat.efiber.data.repository.WorkOrdersRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.WorkOrder
import com.unbuniworks.camusat.efiber.domain.model.WorkOrderDetails
import com.unbuniworks.camusat.efiber.domain.repository.WorkOrdersRepository
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WorkOrdersUseCase(
    private val repository: WorkOrdersRepository = WorkOrdersRepositoryImpl(),
    private val activity: Activity
) {

    fun getWorkOrders():Flow<Resource<List<WorkOrder>>> = flow {
        try {
            val response = repository.getWorkOrders(activity)
            val workOrder = response.map { it.toWorkOrder() }
            emit(Resource.Success(message = "Request was successful", data = workOrder))
        }
        catch (e:IOException){
            e.localizedMessage?.let { Log.e("WorkOrdersStatus", it) }
            emit(Resource.Error( "Couldn't reach server check your internet connection"))
        }
        catch (e: JsonConvertException){
            e.localizedMessage?.let { Log.e("WorkOrdersStatus", it) }
            emit(Resource.Error( "An expected Error Occurred"))
        }

    }

    fun getWorkOrder(workOrderId:String):Flow<Resource<WorkOrderDetails>> = flow {
        try {
            val response = repository.getWorkOrder(workOrderId = workOrderId, activity = activity)
            val workOrder = response.toWorkOrder()
            emit(Resource.Success(message = "Request was successful", data = workOrder))
        }
        catch (e:IOException){
            e.localizedMessage?.let { Log.e("WorkOrdersStatus", it) }
            emit(Resource.Error( "Couldn't reach server check your internet connection"))
        }
        catch (e: JsonConvertException){
            e.localizedMessage?.let { Log.e("WorkOrdersStatus", it) }
            emit(Resource.Error( "An expected Error Occurred"))
        }

    }

}