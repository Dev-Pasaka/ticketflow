package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.util.Log
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.toWorkOrderDetails
import com.unbuniworks.camusat.efiber.data.repository.WorkOrderRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.WorkOrderDetails
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WorkOrderDetailsUseCase(
    private val repository: WorkOrderRepositoryImpl = WorkOrderRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    fun getWorkOrderDetails(workOrderId: String, activity: Activity): Flow<Resource<WorkOrderDetails>> = flow {
        try {
            emit(Resource.Loading( message = "Loading"))
            val token = sharedPreferenceRepository.getString(Constants.token, activity) ?: ""
            val result = repository.getWorkOrder(workOrderId = workOrderId, token = token).toWorkOrderDetails()
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

