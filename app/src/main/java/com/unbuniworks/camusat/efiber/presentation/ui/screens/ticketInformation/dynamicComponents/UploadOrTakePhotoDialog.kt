package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun UploadOrTakePhotoDialog(
    index:Int,
    imageIndex:Int,
    ticketInformationViewModel: TicketInformationViewModel,
    navController:NavHostController
) {
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            ticketInformationViewModel.openOrCloseImageDialogBox()
        }
    )


    Dialog(
        onDismissRequest = { ticketInformationViewModel.openOrCloseImageDialogBox()},
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        ElevatedCard(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
               Surface(
                   onClick = {
                      // ticketInformationViewModel.openOrCloseImageDialogBox()
                      // singlePhotoPickerLauncher.launch(arrayOf("image/*"))
                       singlePhotoPickerLauncher.launch(
                           PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                       )

                   },
                   color = Color.Transparent
               ) {
                   Column(
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Center
                   ) {
                       Icon(
                           painter = painterResource(id = R.drawable.image),
                           contentDescription ="upload image",
                           tint = colorResource(id = R.color.button_link_color),
                           modifier = Modifier.size(40.dp)
                       )
                       Text(
                           text = "Upload image",
                           color = colorResource(id = R.color.button_link_color)
                       )

                   }
               }
                Surface(
                    onClick = {
                        ticketInformationViewModel.selectImageIndex(
                            index = index,
                            imageIndex = imageIndex
                        )
                        navController.navigate(Screen.TakePhoto.route)
                        ticketInformationViewModel.openOrCloseImageDialogBox()
                    },
                    color = Color.Transparent
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.photo),
                            contentDescription ="Take photo",
                            tint = colorResource(id = R.color.button_link_color),
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = "Take photo",
                            color = colorResource(id = R.color.button_link_color)
                        )

                    }
                }

            }
        }
    }
}






