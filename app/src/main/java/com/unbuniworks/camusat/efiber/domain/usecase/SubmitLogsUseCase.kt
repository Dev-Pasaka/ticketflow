package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.content.SharedPreferences
import androidx.compose.ui.unit.Constraints
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.SubmitUserLogsDto
import com.unbuniworks.camusat.efiber.data.repository.UserRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.repository.UserRepository

class SubmitLogsUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),
    private val sharedPreferences: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    suspend fun submitLogs(logs: SubmitUserLogsDto, activity: Activity):Boolean  = try{
        val token = sharedPreferences.getString(key = Constants.token, activity = activity) ?: ""
        val result = userRepository.submitUserLogs(logs, token)
        result["status"] == true
    }catch (e:Exception){
        false
    }
}