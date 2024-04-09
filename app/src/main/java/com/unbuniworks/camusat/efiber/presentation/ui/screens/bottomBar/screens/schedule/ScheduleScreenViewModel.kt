package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.domain.usecase.GetScheduledWorkOrdersUseCase
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.components.ScheduleItem
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.model.Schedule
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ScheduleScreenViewModel(
    private val getScheduledWorkOrdersUseCase:GetScheduledWorkOrdersUseCase = GetScheduledWorkOrdersUseCase()
):ViewModel() {
    var search by mutableStateOf("")
        private set

    var isDatePickerOpen by mutableStateOf(false)
        private set

    var selectedDate by mutableLongStateOf(1578096000000)
        private set
    fun updateSelectedDate(date:Long){
        selectedDate  = date
    }
    fun openOrCloseDatePicker(){
        isDatePickerOpen = !isDatePickerOpen
    }

    private var originalState by mutableStateOf(GetScheduledWorkOrderState())

    var getScheduledWorkOrderState by mutableStateOf(GetScheduledWorkOrderState())
        private set

    private fun getScheduledWorkOrders(){
        viewModelScope.launch {
            getScheduledWorkOrderState = GetScheduledWorkOrderState(isLoading = true)
            val result = getScheduledWorkOrdersUseCase.invoke()
            when(result){
                is Resource.Success ->{
                    originalState = GetScheduledWorkOrderState(isLoading = false, data = result.data ?: emptyList())
                    getScheduledWorkOrderState = GetScheduledWorkOrderState(isLoading = false, data = result.data ?: emptyList())
                }
                is Resource.Loading ->{

                }
                is Resource.Error ->{
                    getScheduledWorkOrderState = GetScheduledWorkOrderState(isLoading = false, data = emptyList(), error = result.message ?: "")
                }
            }
        }
    }

    fun updateSearch(searchValue: String) {
        search = searchValue
        getScheduledWorkOrderState = getScheduledWorkOrderState.copy(data = originalState.data.filter {
            it.id.contains(searchValue, ignoreCase = true) || it.name.contains(searchValue, ignoreCase = true)
        })
    }
    fun formatDate() :String{
        val date = Date(selectedDate)
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return format.format(date)
    }

    init {
        getScheduledWorkOrders()
    }
}