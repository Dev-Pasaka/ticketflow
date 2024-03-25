package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun ViewImage(
    index: Int,
    imageIndex: Int,
    ticketInformationViewModel: TicketInformationViewModel,
    navController: NavHostController
) {
    Dialog(
        onDismissRequest = { ticketInformationViewModel.openOrCloseImageViewDialog() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .padding(horizontal = 4.dp, vertical = 4.dp),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {

                Image(
                    painter = rememberAsyncImagePainter(Uri.EMPTY),
                    contentDescription = "Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize(0.8f)
                        .clip(RoundedCornerShape(10.dp))
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Surface(
                        onClick = {
                                  ticketInformationViewModel.openOrCloseImageViewDialog()
                        },
                        color = Color.Transparent
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Take photo",
                                tint = Color.Red.copy(alpha = 0.3f),
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = "Close",
                                color = Color.Red.copy(alpha = 0.3f),
                            )

                        }

                    }
                    Surface(
                        onClick = {
                            ticketInformationViewModel.selectImageIndex(
                                index = index,
                                imageIndex = imageIndex
                            )
                            navController.navigate(Screen.TakePhoto.route){
                            }
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
                            Text(
                                text = "Retake",
                                color = colorResource(id = R.color.button_link_color)
                            )

                        }
                    }
                }
            }
        }
    }
}