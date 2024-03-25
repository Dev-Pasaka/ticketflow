package com.unbuniworks.camusat.efiber.presentation.ui.screens.auth

import com.unbuniworks.camusat.efiber.domain.model.User

data class LoginState(
    val isLoading:Boolean = false,
    val error:String? = null,
    val user:User? = null,
    val isSuccessful:Boolean = false
)