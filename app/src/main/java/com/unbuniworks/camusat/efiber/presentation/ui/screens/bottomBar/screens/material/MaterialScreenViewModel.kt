package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material

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

class MaterialScreenViewModel(
    private val useCase: MaterialsUseCase = MaterialsUseCase(),
):ViewModel() {

    init {
        getMaterials()
    }
    var materialsState:MaterialsState? by mutableStateOf(null)
        private set

    private fun getMaterials() {
        useCase.getMaterials().onEach { result ->
            materialsState = when (result) {
                is Resource.Success -> {
                    Log.e("Material", result.data.toString())
                    MaterialsState(data = result.data ?: emptyList(), error = result.message.toString(),)
                }
                is Resource.Error -> {
                    Log.e("Material", result.data.toString())
                    MaterialsState(error = result.message.toString(), data = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    Log.e("Material", result.data.toString())
                    MaterialsState(isLoading = true, data = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }


}