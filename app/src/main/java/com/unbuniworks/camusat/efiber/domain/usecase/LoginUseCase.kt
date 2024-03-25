package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
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
import com.unbuniworks.camusat.efiber.data.repository.UserRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.User
import com.unbuniworks.camusat.efiber.domain.repository.UserRepository
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginUseCase(
    private val repository:UserRepository = UserRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl(),
    private val activity: Activity
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun login(userCredentials: UserCredentials):Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading(message = "Loading"))
            val response = repository.login(userCredentials)
            val user = response.toUser()
            if (response.status){
                emit(Resource.Success(data = user, message = "Request was successful"))
                sharedPreferenceRepository.setString(
                    key = Constants.token,
                    value = response.accessToken,
                    activity = activity
                )
            }else{
                emit(Resource.Error( "Login failed"))
            }
        }
        catch (e:IOException){
            e.localizedMessage?.let { Log.e("LoginStatus", it.toString()) }
            emit(Resource.Error( "Couldn't reach server check your internet connection"))
        }
        catch (e:JsonConvertException){
            e.localizedMessage?.let { Log.e("LoginStatus", it.toString()) }
            emit(Resource.Error( "Email or Password can't be empty"))
        }
        catch (e:NullPointerException){
            e.localizedMessage?.let { Log.e("LoginStatus", it.toString()) }
            emit(Resource.Error( "Wrong email or password"))
        }
    }
}



