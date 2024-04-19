package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.util.Log
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.toWorkOrderDetails
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
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {

    suspend fun getWorkOrders(activity: Activity):Resource<List<WorkOrder>>{
        return try {
            val token = sharedPreferenceRepository.getString(Constants.token,activity =activity ) ?: ""
            val response = repository.getWorkOrders(token = token)
            val workOrder = response.map { it.toWorkOrder() }
            Resource.Success(data = workOrder, message = "Success")
        }
        catch (e:IOException){
            e.localizedMessage?.let { Log.e("WorkOrdersStatus", it) }
            Resource.Error(data = emptyList(),message = "No internet connection")
        }
        catch (e:Exception){
            e.localizedMessage?.let { Log.e("WorkOrdersStatus", it) }
            Resource.Error(data = emptyList(), message = "An expected error occurred")

        }

    }


}

suspend fun main(){

}