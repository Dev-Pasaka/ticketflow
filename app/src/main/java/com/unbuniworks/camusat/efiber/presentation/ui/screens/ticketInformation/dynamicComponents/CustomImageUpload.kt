package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import android.util.Log
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
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun CustomImageUpload(
    index: Int,
    navController: NavHostController,
    ticketInformationViewModel: TicketInformationViewModel,
) {
    var selectedImageIndex:Int? by remember {
        mutableStateOf(null)
    }

        Surface(
            onClick = {
                ticketInformationViewModel.openOrCloseImageDialogBox()
                selectedImageIndex = 0
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
                    text = "",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(5.dp))


                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "",
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        painter = painterResource(id = R.drawable.image),
                        contentDescription = "Image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .width(70.dp)
                            .height(60.dp)
                            .padding(2.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .clickable(
                            ) {

                            }
                    )

                }
            }


            if (ticketInformationViewModel.isImageViewDialogOpen) {
                ViewImage(
                    index = index,
                    imageIndex = 0,
                    ticketInformationViewModel = ticketInformationViewModel,
                    navController = navController
                )
            }
        }
    }
   /* if (ticketInformationViewModel.isImageDialogOpen) {

            UploadOrTakePhotoDialog(
                index = index,
                imageIndex = it,
                navController = navController,
                ticketInformationViewModel = ticketInformationViewModel
            )

        Log.e("Index", "Index=$index  imageIndex = $selectedImageIndex" )
    }*/








