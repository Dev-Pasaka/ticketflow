package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ChangeLanguageUseCase(
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    suspend fun newLanguage(newLanguage:String, activity:Activity):Boolean{
        return sharedPreferenceRepository.setString(
            key = Constants.language,
            value = newLanguage,
            activity = activity
        )
    }
}