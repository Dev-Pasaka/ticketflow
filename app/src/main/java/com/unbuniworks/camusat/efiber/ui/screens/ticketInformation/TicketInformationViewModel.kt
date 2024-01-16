package com.unbuniworks.camusat.efiber.ui.screens.ticketInformation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.model.Information
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TicketInformationViewModel():ViewModel() {

    var selectedService by mutableStateOf("")
        private set

    var selectedDate by mutableLongStateOf(1578096000000)
        private set


    val customerInformation = Information(
        project = "Kasarani Installation",
        type = "Subscriber Installation",
        address = "Mirema drive 289",
        client = "Muhamid Tali",
        contact = "0728671678",
        equipment = "Huawei wifi band ZTE 7823"
    )
    val listOfServices = listOf(
        "Schedule Installation",
        "Site Survey",
        "Installation",
        "Speed Test",
        "Material",
        "Problem",
        "Quality Control",

    )
    var isCalenderOpen by mutableStateOf(false)
        private set

    var macId by mutableStateOf("")
        private set
    var serialNo by mutableStateOf("")
        private set
    var gponId by mutableStateOf("")
        private set

    var comments by mutableStateOf("")
        private set
    var speedTest by mutableStateOf("")
        private set

    var speedTestComments by mutableStateOf("")
        private set
    var time by mutableStateOf("")
        private set
    var isTimePickerOpen by mutableStateOf(false)
        private set
    var scheduleInstallationComment by mutableStateOf("")
        private set

    var pigTailQty by mutableStateOf("")
        private set
    var routeQty by mutableStateOf("")
        private set
    var materialComments by mutableStateOf("")
        private set

    var problemComments by mutableStateOf("")
        private set
    fun updateProblemComments(comments:String){
        problemComments = comments
    }
    fun updateMaterialComments(newMaterialComments:String){
        materialComments = newMaterialComments
    }
    fun updatePigTailQty(newPigTailQty:String){
        pigTailQty = newPigTailQty
    }
    fun updateRouteQty(newRouterQty:String){
        routeQty = newRouterQty
    }

    fun updateScheduleInstallationComment(comment:String){
        scheduleInstallationComment = comment
    }

    fun updateTime(setTime:String){
        time = setTime
    }
    fun updateMacId(id:String){macId = id}
    fun updateSerialNo(serial:String){serialNo = serial}
    fun updateGponId(serial:String){gponId = serial}
    fun updateComments(comment:String){comments = comment}
    fun updateSpeedTest(speed:String){speedTest = speed}
    fun updateSpeedTestComments(comment: String){speedTestComments = comment}

    fun openOrCloseTimePicker(){
        isTimePickerOpen = !isTimePickerOpen
    }
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

    fun formatTime(hours: Int, minutes: Int): String {
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val time = Date().apply {
            hours.let { setHours(it) }
            minutes.let { setMinutes(it) }
        }
        return timeFormat.format(time)
    }

    fun launchPhoneDial(context: Context){
        val dialIntent = Intent(Intent.ACTION_DIAL).apply {
        }
        dialIntent.data = Uri.parse("tel:"+ customerInformation.contact)
        try {
            ContextCompat.startActivity(context, dialIntent, null)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    init {
        viewModelScope.launch {
            formatDate()
        }
    }
}