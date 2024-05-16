package com.unbuniworks.camusat.efiber.presentation.ui.screens.auth

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.model.ResetPasswordCredentials
import com.unbuniworks.camusat.efiber.data.remote.model.UserCredentials
import com.unbuniworks.camusat.efiber.domain.usecase.ChangeLanguageUseCase
import com.unbuniworks.camusat.efiber.domain.usecase.GetLanguageUseCase
import com.unbuniworks.camusat.efiber.domain.usecase.LoginUseCase
import com.unbuniworks.camusat.efiber.domain.usecase.ResetPasswordUseCase
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val loginUseCase: LoginUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val changeLanguageUseCase: ChangeLanguageUseCase = ChangeLanguageUseCase(),
    private val getLanguageUseCase: GetLanguageUseCase = GetLanguageUseCase(),
    val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) : ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set


    var isLoginSelected by mutableStateOf("Login")
        private set
    var emailReset by mutableStateOf("")

    var loginState by mutableStateOf(LoginState())
        private set

    var resetPasswordState by mutableStateOf(ResetPasswordState())
        private set

    var selectedLanguage by  mutableStateOf("")
        private set
    var isLanguageDropDownOpen by mutableStateOf(false)
        private set

    fun openOrCloseLanguageDropDown(){isLanguageDropDownOpen = !isLanguageDropDownOpen}





    suspend fun changeLanguage(newLanguage:String, activity: Activity){
        viewModelScope.async {
            changeLanguageUseCase.newLanguage(newLanguage = newLanguage, activity)
            getLanguage(activity =activity)
        }.await()
    }

    suspend fun getLanguage(activity: Activity){
        viewModelScope.launch {
            selectedLanguage = getLanguageUseCase.newLanguage(activity = activity) ?: "en"
        }
    }


    fun updateEmail(emailString: String) {
        email = emailString
    }

    fun updatePassword(passwordString: String) {
        password = passwordString
    }

    fun updateEmailReset(emailString: String) {
        emailReset = emailString
    }

    fun updateLoginState(newState: String) {
        isLoginSelected = newState
    }

    //UKQ1.230917.001
    //23021RAAEG_UKQ1.230917.001
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun login(navController: NavHostController, activity: Activity) {
        val deviceId = "${Build.MODEL}_${Build.ID}"
        Log.e("DeviceId", deviceId)
        loginUseCase.login(
            userCredentials = UserCredentials(
                email = email,
                password = password,
                deviceId = deviceId
            ),
            activity = activity
        ).onEach { result ->
            loginState = when (result) {
                is Resource.Success -> {
                    Log.e("LoginStatus", "Message: ${result.message} Result:${result.data}")
                    if(result.data?.passwordSet != "none" && result.data?.status == true){
                        navController.navigate(route = Screen.UpdatePassword.route) {
                            navController.popBackStack()
                        }
                        LoginState(user = result.data, isSuccessful = true)

                    }else{
                        navController.navigate(route = Screen.SelectModule.route) {
                            navController.popBackStack()
                        }
                        LoginState(user = result.data, isSuccessful = true)
                    }

                }

                is Resource.Error -> {
                    Log.e("LoginStatus", "Message: ${result.message} Result:${result.data}")
                    LoginState(isLoading = false, error = result.message)
                }

                is Resource.Loading -> {
                    Log.e("LoginStatus", "Message: ${result.message} Result:${result.data}")
                    LoginState(isSuccessful = false, isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun resetPassword() {
        viewModelScope.launch {
            resetPasswordState = resetPasswordState.copy(isLoading = true,)
            val result = resetPasswordUseCase.resetPassword(
                ResetPasswordCredentials(
                    email = emailReset,
                )
            )
            resetPasswordState =
                resetPasswordState.copy(isLoading = false, isSuccessful = result.status, message = result.message)

        }

    }




}