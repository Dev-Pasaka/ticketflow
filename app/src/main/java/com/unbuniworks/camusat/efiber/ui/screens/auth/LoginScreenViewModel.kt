package com.unbuniworks.camusat.efiber.ui.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unbuniworks.camusat.efiber.ui.screens.auth.model.LoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoginScreenViewModel:ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set


    var isLoginSelected by mutableStateOf("Login")
        private set
    var emailReset by mutableStateOf("")

    var loginState by mutableStateOf(
        LoginState(
            loading = false,
            isSuccessful = false
        )
    )
        private set

    fun updateEmail(emailString:String){
        email = emailString
    }
    fun updatePassword(passwordString: String){
        password = passwordString
    }
    fun updateEmailReset(emailString: String){
        emailReset = emailString
    }

    fun updateLoginState(newState:String){
        isLoginSelected = newState
    }

    suspend fun login() = withContext(context = Dispatchers.Main.immediate){
        try {
            loginState = loginState.copy(isSuccessful = false, loading = true)
            delay(3000)
            loginState = loginState.copy(isSuccessful = true, loading = false)
            println("Here is login state $loginState")
        }catch (e:Exception){
            loginState = loginState.copy(isSuccessful = false, loading = false)

        }
    }
}