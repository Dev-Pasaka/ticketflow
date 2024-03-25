package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.domain.usecase.WorkOrdersUseCase
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn

class TicketsScreenViewModel(
    private val useCase: WorkOrdersUseCase
):ViewModel() {

    init {
        getWorkOrder()
    }
    var workOrderState by mutableStateOf(WorkOrderState())
        private set


    private fun getWorkOrder(){
        useCase.getWorkOrders().onEach { result ->
            workOrderState = when(result){
                is Resource.Success ->{
                    WorkOrderState(data = result.data ?: emptyList())
                }
                is Resource.Error ->{
                    WorkOrderState(error = result.message.toString())

                }
                is Resource.Loading ->{
                    WorkOrderState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
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


}