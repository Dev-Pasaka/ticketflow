package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.util.Log
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.SubmitEmailTemplateResponseDto
import com.unbuniworks.camusat.efiber.data.repository.WorkOrderRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.PostingWorkOrderResult
import com.unbuniworks.camusat.efiber.domain.repository.WorkOrderRepository
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException

class SubmitEmailTemplateUseCase(
    private val workOrderRepository: WorkOrderRepository = WorkOrderRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
){
    suspend fun submitEmailTemplate(workOrderId:String, templateId:String, activity: Activity): Flow<Resource<SubmitEmailTemplateResponseDto>> = flow{
        val token = sharedPreferenceRepository.getString(key =  Constants.token, activity = activity) ?: ""
        try {
            emit(Resource.Loading(message = "Loading"))
            val response = workOrderRepository.sendEmailTemplate(workOrderId, templateId, token)
            emit(Resource.Success(data = response, message = "Email sent successfully"))
        }
        catch(e: SocketTimeoutException) {
            emit(Resource.Error(message = "Request Timeout"))
        }
        catch (e: IOException){
            e.localizedMessage?.let { Log.e("Failed", it) }
            emit(Resource.Error(message = "Couldn't reach server check your internet connection"))
        }
        catch (e: JsonConvertException){
            e.localizedMessage?.let { Log.e("Failed", it) }
            emit(Resource.Error(message = "Invalid json response"))
        }
        catch (e:Exception){
            e.localizedMessage?.let { Log.e("Failed", it) }
            emit(Resource.Error(message = "An expected Error Occurred"))
        }
    }
}
