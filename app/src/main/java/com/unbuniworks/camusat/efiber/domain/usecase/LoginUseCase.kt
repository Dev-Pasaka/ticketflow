package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.content.Context
import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.ui.unit.Constraints
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.loginDto.toUser
import com.unbuniworks.camusat.efiber.data.remote.model.UserCredentials
import com.unbuniworks.camusat.efiber.data.repository.NotificationRepositoryImpl
import com.unbuniworks.camusat.efiber.data.repository.UserRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.User
import com.unbuniworks.camusat.efiber.domain.repository.NotificationRepository
import com.unbuniworks.camusat.efiber.domain.repository.UserRepository
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginUseCase(
    private val repository: UserRepository = UserRepositoryImpl(),
    private val sendFCMToken: SendFCMToken = SendFCMToken(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun login(userCredentials: UserCredentials, activity: Activity): Flow<Resource<User>> = flow {


        try {
            emit(Resource.Loading(message = "Loading"))
            val response = repository.login(userCredentials)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val fcmToken = activity.getSharedPreferences(
                        "com.unbuniworks.camusat.efiber.PREFERENCE_FILE_KEY",
                        Context.MODE_PRIVATE
                    ).getString(
                        Constants.fcmToken,
                        ""
                    )

                    Log.e("FCMTokenLogin", fcmToken.toString())
                    if (fcmToken != null) {
                        val result = sendFCMToken.sendFCMToken(
                            token = mapOf("token" to fcmToken),
                            authToken = response.accessToken ?: ""
                        )
                        Log.e("FCMTokenResponse", result.toString())
                    }
                } catch (e: Exception) {
                    e.localizedMessage?.let { Log.e("LoginStatus", it) }
                }
            }

            Log.e("User", response.toString())
            sharedPreferenceRepository.setString(
                key = Constants.token,
                value = response.accessToken,
                activity
            )
            sharedPreferenceRepository.setString(
                key = Constants.userId,
                value = response.userDto?.id,
                activity
            )
            sharedPreferenceRepository.setString(
                key = Constants.tokenExpiration,
                value = ((System.currentTimeMillis() / 1000) + 60*45).toString(),
                activity
            )

            val user = response.toUser()
            if (response.status) {
                sharedPreferenceRepository.setString(key = Constants.isLoggedIn, "true", activity)
                sharedPreferenceRepository.setString(
                    key = Constants.email,
                    userCredentials.email,
                    activity
                )
                sharedPreferenceRepository.setString(
                    key = Constants.userId,
                    response.userDto?.id,
                    activity
                )
                emit(Resource.Success(data = user, message = "Request was successful"))
            } else {
                Log.e("DeviceIdResponse", response.toString())
                emit(Resource.Error(response.message))
            }





        } catch (e: IOException) {
            e.localizedMessage?.let { Log.e("LoginStatus", it.toString()) }
            emit(Resource.Error("Couldn't reach server check your internet connection"))
        } catch (e: JsonConvertException) {
            e.localizedMessage?.let { Log.e("LoginStatus", it.toString()) }
            emit(Resource.Error("Email or Password can't be empty"))
        } catch (e: NullPointerException) {
            e.localizedMessage?.let { Log.e("LoginStatus", it.toString()) }
            emit(Resource.Error("Wrong email or password"))
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.e("LoginStatus", it.toString()) }
            emit(Resource.Error("AN expected error occurred"))

        }
    }
}



