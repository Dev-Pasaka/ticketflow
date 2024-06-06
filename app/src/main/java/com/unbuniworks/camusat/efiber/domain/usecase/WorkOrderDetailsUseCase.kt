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
            val workOrderDetails = repository.getWorkOrder(workOrderId = workOrderId, token = token).toWorkOrderDetails()
            emit(
                Resource.Success(
                    data =  workOrderDetails ,
                    message = "Data fetch was successful"
                )
            )

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
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJmYmYwZmY1Yi1kNzI4LTQwZmItOWI0Yy00YzYzZWYwZDkzZmMiLCJlbWFpbCI6ImRldi5wYXNha2FAZ21haWwuY29tIiwiaWF0IjoxNzE1MDE1NjM5LCJleHAiOjE3MTUwMTg2Mzl9.p-GdmCVOEGVnnnXpeR5JXRYiUyK72njxSj6u-jCvhcg"
    val workOrderId = "25521f5b-6a62-4158-886c-9e9534c94b99"
    println(
        WorkOrderRepositoryImpl().getWorkOrder(workOrderId = workOrderId, token = token).toWorkOrderDetails()
    )
}