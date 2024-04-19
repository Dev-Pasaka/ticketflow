package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile

import android.app.Activity
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.UserData
import com.unbuniworks.camusat.efiber.domain.usecase.GetUserUseCase
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile.model.ProfileItems
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val useCase:GetUserUseCase = GetUserUseCase(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
):ViewModel() {

    var getUserDataState by mutableStateOf(GetUserDataState())
        private set


    fun getUser(context:Context){
        viewModelScope.launch {
            val userId= sharedPreferenceRepository.getString(key = Constants.userId, context as Activity) ?: ""
            getUserDataState = GetUserDataState(isLoading = true)
            when(val result = useCase.getUser(userId, activity = context)){
                is Resource.Loading ->{

                }
                is Resource.Error ->{
                    getUserDataState = GetUserDataState(isLoading = false, data = result.data ?: UserData(), error = result.message ?: "")
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                }
                is Resource.Success ->{
                    getUserDataState = GetUserDataState(isLoading = false, data = result.data?.copy(buildNumber = "${Build.MODEL}_${Build.ID}") ?: UserData())
                }
            }
        }
    }

}