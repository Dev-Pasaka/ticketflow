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
import androidx.navigation.NavHostController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.data.remote.dto.workOrderDto.Feature
import com.unbuniworks.camusat.efiber.presentation.navigation.Screen
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel

@Composable
fun Location(
    index:Int,
    feature: Feature,
    navController:NavHostController,
    ticketInformationViewModel: TicketInformationViewModel
) {

    val context = LocalContext.current
    val fusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    val coordinates = if(
    ticketInformationViewModel.currentLocation.latitude > 0.0
            || ticketInformationViewModel.currentLocation.longitude > 0.0
    ) """
                    Coordinates
                    Lat: ${ticketInformationViewModel.currentLocation.latitude}
                    Long: ${ticketInformationViewModel.currentLocation.longitude}
                """.trimIndent()
    else "Loading coordinates"

    ticketInformationViewModel.getCurrentLocation(fusedLocationProviderClient = fusedLocationProviderClient, index= index)
    Surface(
        enabled = if(
            ticketInformationViewModel.currentLocation.latitude > 0.0
            || ticketInformationViewModel.currentLocation.longitude > 0.0
        )  true else false,
        onClick = {
                  navController.currentBackStackEntry?.arguments?.putString(
                      "coordinates", Pair(ticketInformationViewModel.currentLocation.latitude,
                          ticketInformationViewModel.currentLocation.longitude
                      ).toString()
                  )
            navController.navigate(Screen.Maps.route)
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
                text = coordinates,
                fontSize = 14.sp
            )
            Icon(painter = painterResource(id = R.drawable.map), contentDescription ="Location" )
        }
    }



}
