package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.FontScaling
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.TicketInformationViewModel


@Composable
fun MapsAndLocation(
    ticketInformationViewModel: TicketInformationViewModel,
    navController: NavHostController,
    index: String
) {

    var markerState  = rememberMarkerState(position = ticketInformationViewModel.location)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ticketInformationViewModel.location, 11f)
    }
    val context: Context = LocalContext.current

    var uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = true,
                myLocationButtonEnabled = true,
                compassEnabled = true
            )
        )
    }
    var properties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.TERRAIN,
                isMyLocationEnabled = true,
                isBuildingEnabled = true,
                isTrafficEnabled = true,

            )
        )
    }


    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings,
            onMapLoaded = {

            },

            onMapLongClick = { latLng ->
                ticketInformationViewModel.updateLocation(latLng)
                Log.e("Live Location", latLng.toString())


            },
            onMyLocationClick = { latlng ->
                ticketInformationViewModel.updateLocation(LatLng(latlng.latitude, latlng.longitude))
                Toast.makeText(context, "Location selected ", Toast.LENGTH_SHORT).show()
            }

        ) {
            Marker(
                state = markerState,
                title = ticketInformationViewModel.getLocationName(
                    ticketInformationViewModel.location, context
                ),
                snippet = "Location",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE),
                onInfoWindowClick = {
                    ticketInformationViewModel.selectLocation(index = index.toInt())
                    Toast.makeText(
                        context,
                        "Location selected",
                        Toast.LENGTH_SHORT).show()
                }

            ){

            }
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(100),
                    color = colorResource(id = R.color.button_color),
                    modifier = Modifier.padding(16.dp),
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close map",
                            tint = Color.White
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 7.dp, vertical = 8.dp)
            ) {
                ElevatedCard(
                    modifier = Modifier.width(200.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            Text(
                                text = "Location Coordinates",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.button_color),
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Coordinates" ,
                                modifier = Modifier.size(16.dp)
                            )

                        }
                        Text(
                            text = "Name: ${ticketInformationViewModel.getLocationName(
                                latLng = ticketInformationViewModel.location,
                                context = context
                            )}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.button_color),
                        )
                        Text(
                            text = """
                            Lat: ${ticketInformationViewModel.location.latitude}
                            Lng: ${ticketInformationViewModel.location.longitude}
                        """.trimIndent(),
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.button_color),
                        )
                    }
                }
                Switch(
                    modifier = Modifier
                        .padding(horizontal = 50.dp),
                    checked = uiSettings.zoomControlsEnabled,
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = colorResource(id = R.color.button_color),
                    ),
                    onCheckedChange = {
                        uiSettings = uiSettings.copy(zoomControlsEnabled = it)
                        properties = if (it) {
                            properties.copy(mapType = MapType.TERRAIN)
                        } else {
                            properties.copy(mapType = MapType.SATELLITE)
                        }
                    }
                )
            }
        }


    }
}


@Composable
fun GPSLocation(
    ticketInformationViewModel: TicketInformationViewModel,
    navController: NavHostController,
    index:Int
){
    Surface(
        onClick = {
            navController.navigate("maps/$index")
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
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "G.P.S Location",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "( Lat: ${ticketInformationViewModel.location.latitude}, Lng: ${ticketInformationViewModel.location.longitude}) ",
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.button_color)
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                painter = painterResource(id = R.drawable.map),
                contentDescription ="map"
            )
        }
    }
}





class LocationCallback(private val onLocationUpdate: (LatLng) -> Unit) :
    com.google.android.gms.location.LocationCallback() {

    override fun onLocationResult(locationResult: com.google.android.gms.location.LocationResult) {
        val lastLocation = locationResult.lastLocation
        onLocationUpdate.invoke(LatLng(lastLocation?.latitude ?: 0.0, lastLocation?.longitude ?: 0.0))
    }
}