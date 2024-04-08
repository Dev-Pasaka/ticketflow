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
                sharedPreferenceRepository.getString(key = Constants.isLoggedIn, activity = activity) == Constants.isLoggedIn

            }catch (e:Exception){
                false
            }
        }.await()

    }

}