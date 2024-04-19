package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.unbuniworks.camusat.efiber.common.Resource
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import com.unbuniworks.camusat.efiber.domain.usecase.PostWorkOrderTaskUseCase
import com.unbuniworks.camusat.efiber.domain.usecase.WorkOrderDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.net.URL
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class TicketInformationViewModel(
    private val workOrderDetailsUseCase: WorkOrderDetailsUseCase = WorkOrderDetailsUseCase(),
    private val postWorkOrderTaskUseCase: PostWorkOrderTaskUseCase = PostWorkOrderTaskUseCase(),
    private val orderId: String,
    private val activity: Activity

) : ViewModel() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    var workOrderDetailState by mutableStateOf(WorkOrderDetailState())
        private set

    var selectedStatus by mutableStateOf("")
        private set

    fun updateStatus(status:String){selectedStatus = status}


    var isBottomSheetOpen by mutableStateOf(false)
        private set

    private var _currentFeaturesList: MutableStateFlow<List<Feature>> =
        MutableStateFlow(emptyList())
    val currentFeaturesList: StateFlow<List<Feature>> = _currentFeaturesList

    var isSpecialFeature by mutableStateOf(false)
        private set
    var featureName by mutableStateOf("")
        private set




    var currentDate by mutableStateOf("")
        private set

    var currentTime by mutableStateOf("")
        private set
    var currentImage by mutableStateOf("")
        private set
    var currentTakenPhoto  by mutableStateOf("")
        private set
    var currentDropDown by mutableStateOf("")
        private set

    fun selectDate(key:String){ currentDate = key }
    fun selectTime(key:String){ currentTime = key }
    fun selectImage(key:String){ currentImage = key }
    fun takePhoto(key:String){currentTakenPhoto = key}
    fun selectDropDown(key:String){currentDropDown = key}


    var originalCurrentFeatureList = mutableStateListOf<Feature>()
        private set
    var currentFeatureId by mutableStateOf("")
        private set
    var currentButtonName by mutableStateOf("")
        private set
    val workOrderId get() = orderId


    var iscancelAlertDialogOpen by mutableStateOf(false)
        private set
    var isViewImageBottomSheetOpen by mutableStateOf(false)
        private set
    var isTakePhotoOrUploadImageDialogOpen by mutableStateOf(false)
        private set
    var isDatePickerOpen by mutableStateOf(false)
        private set
    var isTimePickerOpen by mutableStateOf(false)
        private set
    var currentLocation by mutableStateOf(LatLng(0.0, 0.0))
        private set

    var postingWorkOrderState by mutableStateOf(PostingWorkOrderState())
        private set


    fun updateCurrentTemplates(
        openOrCloseBottomSheet: Boolean,
        currentFeatures: List<Feature>,
        featureId: String,
        buttonName: String,
        isFeature:Boolean,
        featureNm:String
    ) {
        isBottomSheetOpen = openOrCloseBottomSheet
        _currentFeaturesList.value = emptyList()
        _currentFeaturesList.value = currentFeatures
        originalCurrentFeatureList.addAll(currentFeatures)
        currentFeatureId = featureId
        currentButtonName = buttonName
        isSpecialFeature = isFeature
        featureName = featureNm

    }

    fun openOrCloseBottomSheet() {
        isBottomSheetOpen = !isBottomSheetOpen
    }

    fun openOrCloseCancelAlertDialog() {
        iscancelAlertDialogOpen = !iscancelAlertDialogOpen
    }

    fun openOrCloseViewImageBottomSheet() {
        isViewImageBottomSheetOpen = !isViewImageBottomSheetOpen
    }

    fun openOrCloseTakePhotoOrUploadImage(key: String) {
        currentTakenPhoto = key
        isTakePhotoOrUploadImageDialogOpen = !isTakePhotoOrUploadImageDialogOpen
    }

    fun openOrCloseDatePicker() {
        isDatePickerOpen = !isDatePickerOpen
    }

    fun openOrCloseTimePicker() {
        isTimePickerOpen = !isTimePickerOpen
    }


    fun formatDate(selectedDate: Long): String {
        val date = Date(selectedDate)
        val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return format.format(date)

    }

    fun convertToUtc(inputTime: String): String {
        // Create a SimpleDateFormat object to parse the input time string
        val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        // Set the time zone to UTC
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        // Parse the input time string
        val date = inputFormat.parse(inputTime)

        // If parsing fails, return an empty string
        if (date == null) {
            return ""
        }

        // Format the time in UTC using the same 24-hour clock format
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getTimeZone("UTC")
        return outputFormat.format(date)
    }

    fun convertFromUtc(inputTime: String): String {
        // Create a SimpleDateFormat object to parse the input time string
        val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        // Set the time zone to UTC
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        // Parse the input time string
        val date = inputFormat.parse(inputTime)

        // If parsing fails, return an empty string
        if (date == null) {
            return ""
        }

        // Format the time in the 12-hour clock format with AM/PM indicator
        val outputFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault()
        return outputFormat.format(date)
    }

    fun updateTextComponent(index: Int, text: String, inputType: String) {
        val updatedFeaturesList = currentFeaturesList.value.mapIndexed { featureIndex, feature ->
            if (index == featureIndex && feature.inputType == inputType) {
                feature.copy(value = text)
            } else {
                feature
            }
        }
        _currentFeaturesList.value = updatedFeaturesList
    }

    fun updateNumberComponent(index: Int, text: String, inputType: String) {
        val updatedFeaturesList = currentFeaturesList.value.mapIndexed { featureIndex, feature ->
            if (index == featureIndex && feature.inputType == inputType) {
                feature.copy(value = text)
            } else {
                feature
            }
        }
        _currentFeaturesList.value = updatedFeaturesList
    }


    fun updateDateComponent(index: Int, date: String) {
        val updatedFeaturesList = currentFeaturesList.value.mapIndexed { featureIndex, feature ->
            if (index == featureIndex) {
                feature.copy(value = date)
            } else {
                feature
            }
        }
        _currentFeaturesList.value = updatedFeaturesList
    }

    fun updateTimeComponent(index: Int, time: String) {
        val updatedFeaturesList = currentFeaturesList.value.mapIndexed { featureIndex, feature ->
            if (index == featureIndex) {
                feature.copy(value = time)
            } else {
                feature
            }
        }
        _currentFeaturesList.value = updatedFeaturesList
    }

    fun selectDropdownComponent(index: Int, text: String) {
        val updatedFeaturesList = currentFeaturesList.value.mapIndexed { featureIndex, feature ->
            if (index == featureIndex) {
                feature.copy(value = text)
            } else {
                feature
            }
        }
        _currentFeaturesList.value = updatedFeaturesList
    }


    fun selectImage(index: Int, uri: Uri) {

        val updatedFeaturesList = currentFeaturesList.value.mapIndexed { featureIndex, feature ->
            if (index == featureIndex) {
                feature.copy(value = uri.toString())
            } else {
                feature
            }
        }
        _currentFeaturesList.value = updatedFeaturesList
    }


    fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "Title")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }

        val uri =
            context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        uri?.let { imageUri ->
            context.contentResolver.openOutputStream(imageUri)?.use { outputStream ->
                outputStream.write(bytes.toByteArray())
            }
        }

        return uri ?: throw RuntimeException("Failed to insert image into MediaStore")
    }


    fun getWorkOrder(orderId: String) {
        workOrderDetailsUseCase.getWorkOrderDetails(workOrderId = orderId, activity = activity).onEach { result ->
            workOrderDetailState = when (result) {
                is Resource.Loading -> {
                    WorkOrderDetailState(isLoading = true)
                }

                is Resource.Success -> {
                    WorkOrderDetailState(data = result.data)

                }

                is Resource.Error -> {
                    WorkOrderDetailState(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)

    }

    fun event(event: TicketInformationEvents) {
        when (event) {
            is TicketInformationEvents.SelectTemplate -> {
                workOrderDetailState = workOrderDetailState.copy(
                    selectedTemplate = event.templateName,
                    isTemplatesDialogBoxOpen = true
                )
            }

            is TicketInformationEvents.OpenOrCloseTemplatesBottomSheet -> {
                workOrderDetailState = workOrderDetailState.copy(
                    isTemplatesDialogBoxOpen = !workOrderDetailState.isTemplatesDialogBoxOpen,
                )

            }

            is TicketInformationEvents.PostWorkOrderTask -> {
                viewModelScope.launch {
                    postingWorkOrderState = PostingWorkOrderState(isLoading = true)

                    val result = postWorkOrderTaskUseCase.postWorkOrdersTask(event.workOrderTaskDto, event.activity)
                    Log.e("PostingResult", result.toString())
                    if (result.status){
                        postingWorkOrderState = PostingWorkOrderState(isLoading = false, isSuccessFul = true, message = result.message)
                        Toast.makeText(event.activity, result.message, Toast.LENGTH_LONG).show()

                    }else{
                        postingWorkOrderState = PostingWorkOrderState(isLoading = false, isSuccessFul = false, message = result.message)
                        Toast.makeText(event.activity, result.message, Toast.LENGTH_LONG).show()

                    }

                }
            }
            is TicketInformationEvents.Refresh ->{
               viewModelScope.launch {
                   postingWorkOrderState = PostingWorkOrderState(isLoading = true)
                   getWorkOrder(orderId)
                   postingWorkOrderState = PostingWorkOrderState(isLoading = false)

               }
            }
        }
    }


    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        index: Int,
        priority: Boolean = true,
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        // Determine the accuracy priority based on the 'priority' parameter
        val accuracy = if (priority) Priority.PRIORITY_HIGH_ACCURACY
        else Priority.PRIORITY_BALANCED_POWER_ACCURACY

        // Retrieve the current location asynchronously
        fusedLocationProviderClient.getCurrentLocation(
            accuracy, CancellationTokenSource().token,
        ).addOnSuccessListener { location ->
            location?.let {
                // If location is not null, invoke the success callback with latitude and longitude
                currentLocation = LatLng(it.latitude, it.longitude)
                val updatedFeaturesList = currentFeaturesList.value.mapIndexed { featureIndex, feature ->
                    if (index == featureIndex) {
                        feature.copy(value = "{lat:${it.latitude}, lng:${it.longitude}}")
                    } else {
                        feature
                    }
                }
                _currentFeaturesList.value = updatedFeaturesList
            }
        }.addOnFailureListener { exception ->
            // If an error occurs, invoke the failure callback with the exception
        }
    }

    fun openPhoneApp(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = android.net.Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(intent)
    }

    fun openEmailApp(context: Context, emailAddress: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = android.net.Uri.parse("mailto:$emailAddress")
        }
        context.startActivity(Intent.createChooser(intent, "Send email"))
    }



    init {
        getWorkOrder(orderId)
    }


}