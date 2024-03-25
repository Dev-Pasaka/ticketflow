package com.unbuniworks.camusat.efiber.presentation.ui.screens.auth

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.remote.model.UserCredentials
import com.unbuniworks.camusat.efiber.domain.usecase.LoginUseCase
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginScreenViewModel(
    private val loginUseCase: LoginUseCase

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


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun login(navController:NavHostController){
        loginUseCase.login(
            userCredentials = UserCredentials(
                email = email,
                password = password
            )
        ).onEach {result ->
            loginState = when(result){
                is Resource.Success ->{
                    Log.e("LoginStatus", "Message: ${result.message} Result:${result.data}")
                    navController.navigate(route = Screen.SelectModule.route) {
                        navController.popBackStack()
                    }
                    LoginState(user = result.data, isSuccessful = true)

                }

                is Resource.Error ->{
                    Log.e("LoginStatus", "Message: ${result.message} Result:${result.data}")
                    LoginState(isLoading = false,error = result.message)
                }

                is Resource.Loading ->{
                    Log.e("LoginStatus", "Message: ${result.message} Result:${result.data}")
                    LoginState(isSuccessful = false,isLoading = true,)
                }
            }

        }.launchIn(viewModelScope)
    }


}