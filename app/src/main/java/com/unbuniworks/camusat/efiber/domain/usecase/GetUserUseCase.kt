package com.unbuniworks.camusat.efiber.domain.usecase

import android.util.Log
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.remote.dto.getUserDto.GetUserDto
import com.unbuniworks.camusat.efiber.data.remote.dto.getUserDto.toUser
import com.unbuniworks.camusat.efiber.data.repository.UserRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.UserData
import com.unbuniworks.camusat.efiber.domain.repository.UserRepository
import io.ktor.serialization.*
import java.io.IOException

class GetUserUseCase(
    private val repository: UserRepository = UserRepositoryImpl()
) {
    suspend fun getUser(userId:String):Resource<UserData> = try{
        val response = repository.getUserDto(userId)
        val user = response.toUser()
        if (response.status.toBoolean()){
            Resource.Success(data = user, message = response.message)
        }else{
            Resource.Error(data = user, message = response.message)
        }
    }catch (e: IOException){
        e.localizedMessage?.let { Log.e("LoginStatus", it) }
        Resource.Error( "Couldn't reach server check your internet connection")
    }
    catch (e:Exception){
        e.localizedMessage?.let { Log.e("LoginStatus", it) }
        Resource.Error( "An expected error occurred")
    }
}