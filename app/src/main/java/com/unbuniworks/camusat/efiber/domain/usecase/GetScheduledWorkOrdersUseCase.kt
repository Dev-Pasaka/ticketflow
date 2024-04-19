package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.icu.text.SimpleDateFormat
import android.util.Log
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto.toScheduledWorkOrder
import com.unbuniworks.camusat.efiber.data.repository.WorkOrdersRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.ScheduledWorkOrders
import com.unbuniworks.camusat.efiber.domain.repository.WorkOrdersRepository
import io.ktor.serialization.*
import io.ktor.utils.io.errors.*

class GetScheduledWorkOrdersUseCase(
    private val workOrdersRepository: WorkOrdersRepository = WorkOrdersRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    suspend fun getScheduledWorkOrders(activity: Activity): Resource<List<ScheduledWorkOrders>> =
        try {
            val token = sharedPreferenceRepository.getString(Constants.token, activity) ?: ""
            val result = workOrdersRepository.getWorkOrders(token = token)
            if (result.isNotEmpty()){
                val scheduledWorkOrders = result.mapIndexed { index, item ->
                    item.toScheduledWorkOrder(index = index)
                }.sortedBy {
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(it.dueDate)
                }.reversed()
                Log.e("ScheduledResult", result.toString())
                Resource.Success(data = scheduledWorkOrders, message = "Successful")
            }else{
                Log.e("ScheduledResult", result.toString())
                Resource.Success(data = emptyList(), message = "Successful")
            }

        } catch (e:IOException){
            e.localizedMessage?.let { Log.e("ScheduledResult", it) }
            Resource.Error( "Couldn't reach server check your internet connection")
        }
        catch (e: JsonConvertException){
            e.localizedMessage?.let { Log.e("ScheduledResult", it) }
            Resource.Error( "Failed to purse json")
        }
        catch (e:NullPointerException){
            e.localizedMessage?.let { Log.e("ScheduledResult", it) }
            Resource.Error( "Wrong email or password")
        }
        catch (e:Exception){
            e.localizedMessage?.let { Log.e("ScheduledResult", it) }
            Resource.Error( "An expected error occurred")
        }
}


