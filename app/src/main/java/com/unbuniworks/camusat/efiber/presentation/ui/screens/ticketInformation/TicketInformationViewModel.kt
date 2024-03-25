package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.remote.dto.templatesDto.TemplatesDtoItem
import com.unbuniworks.camusat.efiber.data.repository.TemplateRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.usecase.WorkOrdersUseCase
import com.unbuniworks.camusat.efiber.presentation.ui.screens.bottomBar.screens.tickets.WorkOrderState
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents.LocationCallback
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.model.Information
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TicketInformationViewModel(
    private val context: Context,
    private val userWorkOrdersUseCase: WorkOrdersUseCase,
):ViewModel() {


    var workOrderState by mutableStateOf(WorkOrderDetailState())
        private set

    var selectedDate by mutableLongStateOf(1578096000000)
        private set

    var isTemplateScrollingInProgress by mutableStateOf(false)
        private set


    var isTemplatesLoading by mutableStateOf(false)

    var openOrCloseDropDown by mutableStateOf(Pair(0, false))
    var isImageProcessing by mutableStateOf(false)
        private set

    var location by mutableStateOf(LatLng(-1.2536520539203622, 36.7996310455012))
        private set

    val fusedLocationClient: FusedLocationProviderClient by mutableStateOf(
        LocationServices.getFusedLocationProviderClient(context)

    )

    fun selectLocation(index: Int){
    }

    private fun getLiveLocation(){
       requestLocationUpdates(
            fusedLocationClient
        ) { updatedLocation ->
            location = updatedLocation
            Log.e("Live Location", updatedLocation.toString())
        }
    }



    fun updateLocation(newLocation:LatLng){
        location = newLocation
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates(
        fusedLocationClient: FusedLocationProviderClient,
        onLocationUpdate: (LatLng) -> Unit
    ) {
        fusedLocationClient.requestLocationUpdates(
            LocationRequest.create(),
            LocationCallback(onLocationUpdate),
            null
        )
    }

    fun getLocationName(latLng: LatLng, context:Context): String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses: List<Address> = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)?.toList() ?: emptyList()
            if (addresses.isNotEmpty()) {
                return addresses[0].getAddressLine(0)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun openOrCloseImageProcessingDialogBox(){
        isImageProcessing = !isImageProcessing
    }
    fun openOrCloseDropDown(index: Int){
        if (openOrCloseDropDown.first == index && openOrCloseDropDown.second){
            openOrCloseDropDown = Pair(index, false)
        }else{
            openOrCloseDropDown = Pair(index, true)
        }

    }

    val customerInformation = Information(
        project = "Kasarani Installation",
        type = "Subscriber Installation",
        address = "Mirema drive 289",
        client = "Muhamid Tali",
        contact = "0728671678",
        equipment = "Huawei wifi band ZTE 7823"
    )

    var bitmaps = mutableStateListOf<Bitmap>()
        private set



    var isImageDialogOpen by mutableStateOf(false)
        private set

    var selectedImageAndIndex:Pair<Int, Int>? by mutableStateOf(null)
        private set


    var selectedTemplate:String? by mutableStateOf(null)
        private set

    var isImageViewDialogOpen by mutableStateOf(false)
        private set

    fun updatedSelectedUri(index:Int, imageIndex: Int, uri:Uri?){
    }

    fun openOrCloseImageViewDialog(){
        isImageViewDialogOpen = !isImageViewDialogOpen
    }
    fun selectImageIndex(index: Int, imageIndex:Int){
        selectedImageAndIndex = Pair(first = index, second = imageIndex)
    }
    fun openOrCloseImageDialogBox(){
        isImageDialogOpen = !isImageDialogOpen
    }
    fun selectTemplate(name:String){
        selectedTemplate = name
    }

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


    fun formatDate(selectedDate:Long) :String{
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

    private suspend fun getTemplates(){
        isTemplatesLoading = true
        val result = TemplateRepositoryImpl().getTemplates()
        delay(1000)
        if (result != null) {
        }else{

        }

        isTemplatesLoading = false
    }

  fun updateTextInputFilled(index:Int, textFilledIndex:Int, text:String){
    }

    // Get uri of images from camera function
    fun bitmapToUri(inContext: Context?, inImage: Bitmap): Uri {

        val tempFile = File.createTempFile("temprentpk", ".png")
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes)
        val bitmapData = bytes.toByteArray()

        val fileOutPut = FileOutputStream(tempFile)
        fileOutPut.write(bitmapData)
        fileOutPut.flush()
        fileOutPut.close()
        return Uri.fromFile(tempFile)
    }

    fun getWorkOrder(orderId:String){
        userWorkOrdersUseCase.getWorkOrder(workOrderId = orderId).onEach { result ->
           workOrderState =  when(result){
                is Resource.Loading ->{
                    WorkOrderDetailState(isLoading = true)
                }
                is Resource.Success ->{
                    WorkOrderDetailState(data = result.data)

                }
                is Resource.Error ->{
                    WorkOrderDetailState(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)

    }

    init {
        viewModelScope.launch {
            getTemplates()
        }
        getLiveLocation()
    }
}