package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.components

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun PhotoPicker(ticketInformationViewModel: TicketInformationViewModel, index:Int) {


    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

        // photo picker.
        if (uri != null) {
            ticketInformationViewModel.selectImage(uri = uri, index = index)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

}