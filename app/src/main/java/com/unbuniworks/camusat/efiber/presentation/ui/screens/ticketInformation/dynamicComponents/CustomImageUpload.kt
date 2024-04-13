package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun CustomImageUpload(
    index: Int,
    key:String,
    feature: Feature,
    navController: NavHostController,
    ticketInformationViewModel: TicketInformationViewModel,
) {
    var selectedImageIndex: Int? by remember {
        mutableStateOf(null)
    }

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

            // photo picker.
            if (uri != null) {
                ticketInformationViewModel.selectImage(uri = uri, index = index)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    Surface(
        onClick = {
                  ticketInformationViewModel.openOrCloseTakePhotoOrUploadImage(key = key)
        },
        shape = RoundedCornerShape(5.dp),
        color = colorResource(id = R.color.light_gray),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 2.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {

            Text(
                text = "Select/Take photo",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(5.dp))

            Image(
                painter = if (
                    feature.value?.toUri() == null
                    || feature.value?.toUri() == Uri.EMPTY
                ) painterResource(id = R.drawable.image) else rememberAsyncImagePainter(model = feature.value?.toUri()),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(70.dp)
                    .height(60.dp)
                    .padding(2.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable(
                    ) {
                        if (
                            feature.value?.toUri() == null
                            || feature.value?.toUri() == Uri.EMPTY
                            ) {
                            ticketInformationViewModel.openOrCloseTakePhotoOrUploadImage(key = "$index")
                            // pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        } else {
                            ticketInformationViewModel.selectImage(key = key)
                            ticketInformationViewModel.openOrCloseViewImageBottomSheet()
                        }

                    }
            )


        }


        if (
            ticketInformationViewModel.isViewImageBottomSheetOpen
            && ticketInformationViewModel.currentImage == key
            ) {
            ViewImage(
                index = index,
                feature = feature,
                ticketInformationViewModel = ticketInformationViewModel,
                navController = navController
            )
        }
    }

    if (
        ticketInformationViewModel.isTakePhotoOrUploadImageDialogOpen
        && ticketInformationViewModel.currentTakenPhoto == key
        ) {

        UploadOrTakePhotoDialog(
            index = index,
            navController = navController,
            ticketInformationViewModel = ticketInformationViewModel
        )

        Log.e("Index", "Index=$index  imageIndex = $selectedImageIndex" )
    }

}








