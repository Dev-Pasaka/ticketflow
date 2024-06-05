package com.unbuniworks.camusat.efiber.presentation.ui.screens.splashScreen

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.repository.WorkOrderRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.repository.WorkOrderRepository
import com.unbuniworks.camusat.efiber.domain.usecase.WorkOrderDetailsUseCase
import kotlinx.coroutines.async

class SplashScreenViewModel(
    private val workOrderRepository: WorkOrderRepository = WorkOrderRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()

):ViewModel() {

    suspend fun isTokenValid(activity: Activity):Boolean {
        return viewModelScope.async {
            try {
                val isLoggedIn = sharedPreferenceRepository.getString(key = Constants.isLoggedIn, activity = activity).toBoolean()
                val isExpired = (System.currentTimeMillis()/1000) > (sharedPreferenceRepository.getString(
                    key = Constants.tokenExpiration,
                    activity = activity
                )?.toLong() ?: 0L)
                isLoggedIn && !isExpired
            }catch (e:Exception){
                false
            }
        }.await()

    }

}