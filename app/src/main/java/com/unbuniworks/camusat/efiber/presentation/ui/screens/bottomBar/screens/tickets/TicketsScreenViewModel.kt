package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets

import android.app.Activity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.domain.model.WorkOrder
import com.unbuniworks.camusat.efiber.domain.usecase.WorkOrdersUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class TicketsScreenViewModel(
    private val useCase: WorkOrdersUseCase,
    private val activity: Activity
) : ViewModel() {


    // Initialize isRefreshing to false


    var workOrderState by mutableStateOf(WorkOrderState())


    fun refresh() {
        // Set isRefreshing to true before making the network call
        viewModelScope.launch {
            workOrderState = WorkOrderState(isLoading = true, data = emptyList())
            val result = useCase.getWorkOrders(activity)
            workOrderState = WorkOrderState(isLoading = false, data = result.data ?: emptyList())
        }
    }


    fun getColor(colorString: String): Color {
        val cleanedColorString = cleanAndValidateColorString(colorString)
        return Color(android.graphics.Color.parseColor(cleanedColorString))
    }

    fun cleanAndValidateColorString(colorString: String): String {
        var cleanedColorString = colorString.trim()
        if (!cleanedColorString.startsWith("#")) {
            cleanedColorString = "#$cleanedColorString" // Add '#' prefix if missing
        }
        if (cleanedColorString.length == 4) {
            // If the color string is of format #RGB, convert it to #RRGGBB
            val r = cleanedColorString[1]
            val g = cleanedColorString[2]
            val b = cleanedColorString[3]
            cleanedColorString = "#$r$r$g$g$b$b"
        }
        return cleanedColorString
    }


    init {
        refresh()
    }

}