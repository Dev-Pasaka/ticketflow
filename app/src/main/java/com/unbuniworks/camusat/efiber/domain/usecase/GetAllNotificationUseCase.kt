package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.util.Log
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.notificationsDto.NotificationDto
import com.unbuniworks.camusat.efiber.data.repository.NotificationRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.Notification
import com.unbuniworks.camusat.efiber.domain.repository.NotificationRepository
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException

class GetAllNotificationUseCase(
    private val notificationRepository: NotificationRepository = NotificationRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    suspend fun getAllNotification(activity: Activity): Flow<Resource<List<Notification>>> = flow {
        val  token = sharedPreferenceRepository.getString(key =  Constants.token, activity = activity) ?: ""
        try {
            emit(Resource.Loading(message = "Loading"))
            val response = notificationRepository.getAllNotifications(token = token).toNotificationList()
            emit(Resource.Success(data = response, message = "Notifications fetched successfully"))
        }
        catch (e: SocketTimeoutException) {
            e.localizedMessage?.let { Log.e("Failed", it) }
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