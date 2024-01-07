package com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun TicketInformationMiddleScreen(ticketInformationViewModel: TicketInformationViewModel) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(horizontal = 16.dp,)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Information",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = colorResource(id = R.color.button_color)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .verticalScroll(state = scrollState),
        ) {
            Column {

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Project",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.button_link_color),
                    )
                    Text(
                        text = ticketInformationViewModel.customerInformation.project,
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Type",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.button_link_color),
                    )
                    Text(
                        text = ticketInformationViewModel.customerInformation.type,
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
                Surface (
                    color = colorResource(id = R.color.background),
                    onClick = {
                    }
                ) {

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Address",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.button_link_color),
                        )
                        Text(
                            text = ticketInformationViewModel.customerInformation.address,
                            fontSize = 14.sp,
                            color = Color.DarkGray,
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Client",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.button_link_color),
                    )
                    Text(
                        text = ticketInformationViewModel.customerInformation.client,
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }

                Surface (
                    color = colorResource(id = R.color.background),
                    onClick = {
                        ticketInformationViewModel.launchPhoneDial(context = context)
                    }
                ){
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Contact",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.button_link_color),
                        )
                        Text(
                            text = ticketInformationViewModel.customerInformation.contact,
                            fontSize = 14.sp,
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Equipment",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.button_link_color),
                    )
                    Text(
                        text = ticketInformationViewModel.customerInformation.equipment,
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }

        }
        
    }
}

