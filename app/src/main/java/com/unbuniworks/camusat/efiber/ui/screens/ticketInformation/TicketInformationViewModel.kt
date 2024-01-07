package com.unbuniworks.camusat.efiber.ui.screens.ticketInformation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TicketInformationViewModel():ViewModel() {

    var selectedService by mutableStateOf("")
        private set

    var selectedDate by mutableLongStateOf(1578096000000)
        private set
    val listOfServices = listOf(
        "Schedule Survey",
        "Site Survey",
        "Installation",
        "Speed Test",
        "Material",
        "Problem",
        "Quality Control",

    )
    var isCalenderOpen by mutableStateOf(false)
        private set

    fun openOrCloseCalender(){
        isCalenderOpen = !isCalenderOpen
    }
    fun selectDate(date:Long){
        selectedDate = date
    }
    fun selectService(name:String){
        if (name == selectedService){
            selectedService == ""
        }else{
            selectedService = name

        }
    }

    fun formatDate() :String{
        val date = Date(selectedDate)
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return format.format(date)

    }
    init {
        viewModelScope.launch {
            formatDate()
        }
    }
}