package com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.components.ScheduleItem
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.schedule.model.Schedule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ScheduleScreenViewModel():ViewModel() {
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

    val scheduledItems by mutableStateOf(
        listOf(
            Schedule(scheduleNumber = 389659086,"Casablanca"),
            Schedule(scheduleNumber = 389659086,"Casablanca"),
            Schedule(scheduleNumber = 389659086,"Casablanca"),
            Schedule(scheduleNumber = 389659086,"Casablanca"),
            Schedule(scheduleNumber = 389659086,"Casablanca"),
            Schedule(scheduleNumber = 389659086,"Casablanca"),
        )
    )

    fun updateSearch(searchValue:String){
        search = searchValue
    }
    fun formatDate() :String{
        val date = Date(selectedDate)
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return format.format(date)

    }
}