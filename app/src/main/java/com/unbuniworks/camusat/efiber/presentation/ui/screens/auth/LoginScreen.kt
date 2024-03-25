package com.unbuniworks.camusat.efiber.presentation.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.components.LoginScreenLowerSection
import com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.components.LoginScreenMiddleSection
import com.unbuniworks.camusat.efiber.presentation.ui.screens.auth.components.LoginScreenUpperSection


@Composable
fun LoginsScreen(
    loginScreenViewModel: LoginScreenViewModel,
    actionNavigateToSelectModule:() -> Unit
){
    val context = LocalContext.current

    Surface(
        color = colorResource(id = R.color.background),
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LoginScreenUpperSection(loginScreenViewModel = loginScreenViewModel)
            LoginScreenMiddleSection(loginScreenViewModel = loginScreenViewModel, actionNavigateToSelectModule)
            LoginScreenLowerSection()
        }
    }

    if (!loginScreenViewModel.loginState.error.isNullOrBlank()){
        Toast.makeText(context, loginScreenViewModel.loginState.error, Toast.LENGTH_SHORT).show()
    }else if (loginScreenViewModel.loginState.isSuccessful){
        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()

    }


}