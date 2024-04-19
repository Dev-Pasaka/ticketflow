package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.updatePasswordDto.UpdatePasswordDto
import com.unbuniworks.camusat.efiber.data.repository.UserRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.repository.UserRepository

class UpdatePasswordUseCase(
    private val repository: UserRepository = UserRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    suspend fun updatePassword(newPassword:String, activity: Activity):UpdatePasswordDto{
        val token = sharedPreferenceRepository.getString(Constants.token, activity) ?: ""
        return try {
            repository.updatePassword(newPassword = newPassword, token = token)
        }catch (e:Exception){
            UpdatePasswordDto(
                status = false,
                message = e.localizedMessage?: ""
            )
        }
    }
}