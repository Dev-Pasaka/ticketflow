package com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.material

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unbuniworks.camusat.efiber.ui.screens.bottomBar.screens.material.model.MaterialItem

class MaterialScreenViewModel():ViewModel() {
    var materials by mutableStateOf(
        listOf(
            MaterialItem(title = "Router ZTE 7712", quantity = 8),
            MaterialItem(title = "Cable outdoor", quantity = -6),
            MaterialItem(title = "Pto", quantity = -4),
            MaterialItem(title = "Motorola e562", quantity = 15),
        )
    )
}