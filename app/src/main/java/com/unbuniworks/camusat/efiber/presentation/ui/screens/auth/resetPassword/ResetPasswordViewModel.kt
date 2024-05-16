package com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.resetPassword

import android.app.Activity
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.domain.usecase.UpdatePasswordUseCase
import com.unbuniworks.camusat.efiber.domain.usecase.ValidateResetPasswordUseCase
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import kotlinx.coroutines.launch

class ResetPasswordViewModel(
    private val useCase: UpdatePasswordUseCase = UpdatePasswordUseCase(),
    private val resetPasswordUseCase: ValidateResetPasswordUseCase = ValidateResetPasswordUseCase()
):ViewModel() {
    var newPassword by mutableStateOf("")
        private set
    var confirmNewpPassword by mutableStateOf("")
        private set

    var updatePasswordState by mutableStateOf(UpdatePasswordState())
        private set

    var isNewPasswordVisible by mutableStateOf(false)
        private set
    var isConfirmNewPasswordVisible by mutableStateOf(false)
        private set
    fun updateNewPassword(text:String){newPassword = text}
    fun updateConfirmNewPassword(text:String){confirmNewpPassword = text}

    fun showOrHideNewPassword(){isNewPasswordVisible = !isNewPasswordVisible}
    fun showOrHideConfirmNewPassword(){isConfirmNewPasswordVisible = !isConfirmNewPasswordVisible}


    fun updatePassword(activity: Activity, navController: NavHostController){
        viewModelScope.launch {
            val isPasswordValid = resetPasswordUseCase.validateResetPassword(password = newPassword)
            if (isPasswordValid.first){
                updatePasswordState = updatePasswordState.copy(isLoading = true,)
                val result = useCase.updatePassword(newPassword = newPassword, activity =activity)
                updatePasswordState = updatePasswordState.copy(isLoading = false, isSuccessful =result.status, message = result.message)
                if (result.status){
                    navController.navigate(Screen.SelectModule.route) {
                        navController.popBackStack()
                    }
                }else{
                    Toast.makeText(activity, result.message, Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(activity, isPasswordValid.second, Toast.LENGTH_LONG).show()
            }

        }
    }
}