package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetLanguageUseCase(
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    suspend fun newLanguage(activity: Activity):String?{
        return sharedPreferenceRepository.getString(
            key = Constants.language,
            activity = activity
        )
    }
}
