package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.domain.model.Material
import com.unbuniworks.camusat.efiber.domain.usecase.MaterialsUseCase
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material.model.MaterialItem
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.WorkOrderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MaterialScreenViewModel(
    private val useCase: MaterialsUseCase = MaterialsUseCase(),
    private val activity: Activity
) : ViewModel() {



    var materialsState  by mutableStateOf(MaterialsState())
        private set



    fun refresh() {
        getMaterials()
    }

    private fun getMaterials() {
        viewModelScope.launch {
            materialsState = MaterialsState(isLoading = true)
            val result = useCase.getMaterials(activity)
            materialsState = MaterialsState(isLoading = false, data = result)
        }
    }

    init {
        getMaterials()
    }

}