package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import android.app.Activity
import androidx.compose.foundation.layout.*
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
                        text = ticketInformationViewModel.workOrderDetailState.data?.name ?: "",
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
                        text = ticketInformationViewModel.workOrderDetailState.data?.type ?: "",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
                Surface(
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
                            text = ticketInformationViewModel.workOrderDetailState.data?.address ?: "",
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
                        text = ticketInformationViewModel.workOrderDetailState.data?.client ?: "",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }

                Surface(
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
                            text = "Contact",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.button_link_color),
                        )
                        Text(
                            text = ticketInformationViewModel.workOrderDetailState.data?.contact ?: "",
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
                        text = ticketInformationViewModel.workOrderDetailState.data?.equipment ?: "",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }

        }

    }
}

