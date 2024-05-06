package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material

import android.app.Activity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unbuniworks.camusat.efiber.domain.model.FilterMaterials
import com.unbuniworks.camusat.efiber.domain.model.Material
import com.unbuniworks.camusat.efiber.domain.usecase.GetFilterMaterialsUseCase
import com.unbuniworks.camusat.efiber.domain.usecase.MaterialsUseCase
import kotlinx.coroutines.launch

class MaterialScreenViewModel(
    private val useCase: MaterialsUseCase = MaterialsUseCase(),
    private val getFilterMaterialsUseCase: GetFilterMaterialsUseCase = GetFilterMaterialsUseCase(),
    private val activity: Activity
) : ViewModel() {



    var materialsState  by mutableStateOf(MaterialsState())
        private set
    var isFilterMaterialsDropDownOpen by mutableStateOf(false)
        private set
    fun openOrCloseMaterialsDropDown(){ isFilterMaterialsDropDownOpen = !isFilterMaterialsDropDownOpen}

    var selectedMaterial by mutableStateOf("")
        private set
    var selectedMaterialId by mutableStateOf("")
        private set
    fun selectMaterial(id:String, name:String){
        selectedMaterial = name
        selectedMaterialId = id
        filterMaterials()
    }
    var filterMaterials by mutableStateOf(emptyList<FilterMaterials>())
        private set

    private var originalMaterials by mutableStateOf(emptyList<Material>())


    fun refresh() {
        getMaterials()
    }

    private fun getMaterials() {
        viewModelScope.launch {
            materialsState = MaterialsState(isLoading = true)
            val result = useCase.getMaterials(activity)
            getFilterMaterials()
            materialsState = MaterialsState(isLoading = false, data = result)
            originalMaterials = result
        }
    }

    private suspend  fun getFilterMaterials(){
        filterMaterials = getFilterMaterialsUseCase.getFilterMaterials(activity = activity)
    }

    private fun filterMaterials(){
        materialsState = materialsState.copy(data = originalMaterials)
        materialsState = materialsState.copy(
            data = materialsState.data.filter { it.dispatchProjectId == selectedMaterialId }
        )

    }



    init {
        getMaterials()
    }

}