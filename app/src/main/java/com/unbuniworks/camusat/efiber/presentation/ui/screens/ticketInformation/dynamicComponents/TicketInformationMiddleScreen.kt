package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Utils
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationEvents
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun TicketInformationMiddleScreen(
    ticketInformationViewModel: TicketInformationViewModel,
    lazyListState: LazyListState
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    var scrollingDown by remember { mutableStateOf(true) }

    var phoneNumber by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
    ) {

        if (ticketInformationViewModel.workOrderDetailState.data?.dueDate != null){
            Text(
                text = "Scheduled for ${Utils.formatIsoDateTime(dateTimeString = ticketInformationViewModel.workOrderDetailState.data?.dueDate ?: "")}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.button_color),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Information",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(id = R.color.button_color)
            )

            IconButton(
                onClick = {
                    ticketInformationViewModel.event(
                        event = TicketInformationEvents.Refresh
                    )
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(id = R.color.light_blue),
                    contentColor = Color.White,
                )
            ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .verticalScroll(state = scrollState),
        ) {


            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ){
                items(ticketInformationViewModel.workOrderDetailState.data?.ticketDetails?.size ?: 0){
                    val ticketInfo = ticketInformationViewModel.workOrderDetailState.data?.ticketDetails?.get(it)
                    Surface(
                       // color = if (it.div(2) == 0) Color.White else Color.LightGray,
                        enabled = Regex("^\\+(?:[0-9] ?){6,14}[0-9]$").matches(ticketInfo?.value ?: "")
                                ||Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}").matches(ticketInfo?.key ?: ""),
                        onClick = {
                            if (Regex("^\\+(?:[0-9] ?){6,14}[0-9]$").matches(ticketInfo?.value ?: "")){
                                ticketInformationViewModel.openPhoneApp(context, ticketInfo?.value ?: "" )
                            }else if(Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}").matches(ticketInfo?.key ?: "")){
                                ticketInformationViewModel.openEmailApp(context = context, emailAddress = ticketInfo?.value ?: "")
                            }
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .fillMaxWidth(),
                        ) {
                            Text(
                                text = ticketInfo?.key ?: "",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.button_link_color),
                            )
                            Text(
                                text = ticketInfo?.value ?: "",
                                fontSize = 14.sp,
                                color = if(
                                    Regex("^\\+(?:[0-9] ?){6,14}[0-9]$").matches(ticketInfo?.value ?: "")
                                    ||Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}").matches(ticketInfo?.key ?: "")
                                    ) colorResource(id = R.color.light_blue) else Color.DarkGray
                            )
                        }
                    }
                }
            }

        }

    }
}

