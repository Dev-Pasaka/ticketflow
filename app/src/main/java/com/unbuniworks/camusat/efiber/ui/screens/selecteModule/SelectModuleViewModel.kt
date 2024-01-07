package com.unbuniworks.camusat.efiber.ui.screens.selecteModule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SelectModuleViewModel():ViewModel() {
    var selectedModule by mutableStateOf("Dispatch")
        private set
    var listOfModules by mutableStateOf(
        mutableListOf(
            "Dispatch",
            "Integrate",
            "Construction",
            "Survey",
            "Design",
            "GIS + Inventory",
        )
    )
        private set
    fun updateSelectedModule(name:String){
        selectedModule = name
    }
}