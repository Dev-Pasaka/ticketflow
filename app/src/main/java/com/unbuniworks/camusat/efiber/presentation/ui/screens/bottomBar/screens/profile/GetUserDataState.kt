package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.profile

import com.unbuniworks.camusat.efiber.domain.model.UserData

data class GetUserDataState(
    val isLoading:Boolean = false,
    val error:String = "",
    val data:UserData = UserData()
)
