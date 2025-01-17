package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.util.Log
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderResponseDto
import com.unbuniworks.camusat.efiber.data.remote.dto.PostWorkOrderTaskDto
import com.unbuniworks.camusat.efiber.data.repository.WorkOrderRepositoryImpl
import com.unbuniworks.camusat.efiber.data.repository.WorkOrdersRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.PostingWorkOrderResult
import com.unbuniworks.camusat.efiber.domain.repository.WorkOrderRepository
import com.unbuniworks.camusat.efiber.domain.repository.WorkOrdersRepository
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostWorkOrderTaskUseCase(
    private val workOrderRepository: WorkOrderRepository = WorkOrderRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    suspend fun postWorkOrdersTask(postWorkOrderTaskDto: PostWorkOrderTaskDto,activity: Activity): PostingWorkOrderResult  {
        return try {
            val token = sharedPreferenceRepository.getString(Constants.token, activity) ?: ""
            val response = workOrderRepository.postWorkOrderTask(postWorkOrderTaskDto, activity = activity,  token)
            if (response.status){
                PostingWorkOrderResult(status = true, response.message)
            }else{
                PostingWorkOrderResult(status = false, response.message)
            }
        } catch (e:IOException){
            e.localizedMessage?.let { Log.e("Failed", it) }
            PostingWorkOrderResult(status = false, "Couldn't reach server check your internet connection")
        }
        catch (e: JsonConvertException){
            e.localizedMessage?.let { Log.e("Failed", it) }
            PostingWorkOrderResult(status = false, "Invalid json response")
        }
        catch (e:Exception){
            e.localizedMessage?.let { Log.e("Failed", it) }
            PostingWorkOrderResult(status = false, "An expected Error Occurred")
        }
    }
}