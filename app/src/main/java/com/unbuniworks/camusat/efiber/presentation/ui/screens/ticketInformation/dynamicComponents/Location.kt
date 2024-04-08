package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.location.LocationServices
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun Location(
    index:Int,
    feature: Feature,
    ticketInformationViewModel: TicketInformationViewModel
) {
    val context = LocalContext.current
    val fusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    ticketInformationViewModel.getCurrentLocation(fusedLocationProviderClient = fusedLocationProviderClient, index= index)
    Surface(
        onClick = {

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
                text = """
                    Coordinates
                    Lat: ${ticketInformationViewModel.currentLocation.latitude}
                    Long: ${ticketInformationViewModel.currentLocation.longitude}
                """.trimIndent(),
                fontSize = 14.sp
            )
            Icon(painter = painterResource(id = R.drawable.map), contentDescription ="Location" )
        }
    }
}