package com.unbuniworks.camusat.efiber.domain.usecase

import android.util.Log
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrdersDto.toScheduledWorkOrder
import com.unbuniworks.camusat.efiber.data.repository.WorkOrdersRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.ScheduledWorkOrders
import com.unbuniworks.camusat.efiber.domain.repository.WorkOrdersRepository
import io.ktor.serialization.*
import io.ktor.utils.io.errors.*

class GetScheduledWorkOrdersUseCase(
    private val workOrdersRepository: WorkOrdersRepository = WorkOrdersRepositoryImpl()
) {
    suspend operator fun invoke(): Resource<List<ScheduledWorkOrders>> =
        try {
            val result = workOrdersRepository.getWorkOrders().mapIndexed { index, item ->
                item.toScheduledWorkOrder(index = index)
            }
            Resource.Success(data = result, message = "Successful")
        } catch (e:IOException){
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            Resource.Error( "Couldn't reach server check your internet connection")
        }
        catch (e: JsonConvertException){
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            Resource.Error( "Email or Password can't be empty")
        }
        catch (e:NullPointerException){
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            Resource.Error( "Wrong email or password")
        }
        catch (e:Exception){
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            Resource.Error( "An expected error occurred")
        }
}
