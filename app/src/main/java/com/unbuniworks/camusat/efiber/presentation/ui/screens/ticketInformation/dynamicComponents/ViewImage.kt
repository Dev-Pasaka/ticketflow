package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewImage(
    index: Int,
    feature: Feature,
    ticketInformationViewModel: TicketInformationViewModel,
    navController: NavHostController
) {

    ModalBottomSheet(
        shape = RectangleShape,
        containerColor = Color.White,
        onDismissRequest = {
            ticketInformationViewModel.openOrCloseViewImageBottomSheet()
        }
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Surface(
                    onClick = {
                        ticketInformationViewModel.selectImage(index = index, uri = Uri.EMPTY)
                        ticketInformationViewModel.openOrCloseViewImageBottomSheet()
                    },
                    color = Color.Transparent
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Take photo",
                            tint = Color.Red.copy(alpha = 0.3f),
                            modifier = Modifier.size(30.dp)
                        )

                    }

                }
                Surface(
                    onClick = {
                       ticketInformationViewModel.openOrCloseTakePhotoOrUploadImage(key = "$index")
                    },
                    color = Color.Transparent
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Repeat,
                            contentDescription = "Take photo",
                            tint = colorResource(id = R.color.button_link_color),
                            modifier = Modifier.size(40.dp)
                        )

                    }
                }
            }

            Image(
                painter = rememberAsyncImagePainter(
                    if(feature.value?.contains("workOrderTaskFiles") == true)"${Constants.baseUrl}uploads${feature.value?.toUri()}" else
                    feature.value?.toUri(),
                    onError = {
                        Icons.Default.ErrorOutline
                    },
                    onLoading = {
                        Icons.Default.Loop
                    }
                ),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}
