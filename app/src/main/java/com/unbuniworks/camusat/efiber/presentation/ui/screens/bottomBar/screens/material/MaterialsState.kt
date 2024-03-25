package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.material

import com.unbuniworks.camusat.efiber.domain.model.Material

data class MaterialsState(
    val isLoading:Boolean = false,
    val data:List<Material> = emptyList(),
    val error:String = ""
)