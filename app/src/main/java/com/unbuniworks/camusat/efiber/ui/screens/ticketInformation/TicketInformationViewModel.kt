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

    var macId by mutableStateOf("")
        private set
    var serialNo by mutableStateOf("")
        private set
    var gponId by mutableStateOf("")
        private set

    var comments by mutableStateOf("")
        private set

    fun updateMacId(id:String){macId = id}
    fun updateSerialNo(serial:String){serialNo = serial}
    fun updateGponId(serial:String){gponId = serial}
    fun updateComments(comment:String){comments = comment}

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