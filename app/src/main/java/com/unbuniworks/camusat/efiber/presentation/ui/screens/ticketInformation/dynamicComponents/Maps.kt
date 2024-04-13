package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation.dynamicComponents

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.unbuniworks.camusat.efiber.R
import com.unbuniworks.camusat.efiber.common.Utils
import kotlinx.coroutines.async

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Maps(navController: NavHostController, coordinates: String) {
    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity
    val scrollState = rememberScrollState()

    Log.e("Coordinates", coordinates)
    val crd = Utils.extractCoordinates(coordinates)
    val lat = crd?.first ?: 0.0
    val lng = crd?.second ?: 0.0

    val singapore = LatLng(lat, lng)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }


    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    var isSatelliteModeOn by remember {
        mutableStateOf(false)
    }
    var properties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.NORMAL,
                isBuildingEnabled = true,
                isMyLocationEnabled = true,

            )
        )
    }
    var locationName by remember { mutableStateOf("") }
    LaunchedEffect(key1 = Utils ){
        locationName = scope.async { Utils.getLocationName(latitude = lat, longitude = lng) }.await() ?: "Unknown Location"

    }


    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            cameraPositionState = cameraPositionState,
            modifier = Modifier.matchParentSize(),
            properties = properties,
            uiSettings = uiSettings
        ){
            Marker(
                state = rememberMarkerState(position =  LatLng(lat, lng)),
                title = "Current Location",
                snippet = locationName,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Surface(
                    onClick = {navController.popBackStack()},
                    shape = CircleShape,
                    color = colorResource(id = R.color.button_color)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription ="Close",
                        tint = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 52.dp)
                    .fillMaxWidth()
            ) {
                ElevatedCard(
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = colorResource(id = R.color.button_color)
                    )
                ) {

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                            Text(
                                text = "Coordinates",
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.button_color),
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = "Location",
                                tint = colorResource(id = R.color.button_color)
                            )
                        }
                        Text(
                            text = """
                                Coordinates
                                Lat: $lat
                                Long: $lng
                            """.trimIndent(),
                            color = colorResource(id = R.color.button_color),
                            fontSize = 12.sp
                        )
                    }

                }


                Switch(
                    colors = SwitchDefaults.colors(
                        checkedIconColor = colorResource(id = R.color.button_color),
                        checkedTrackColor = Color.White,
                        checkedBorderColor = colorResource(id = R.color.button_color),
                        checkedThumbColor = colorResource(id = R.color.button_color)
                    ),
                    checked = isSatelliteModeOn,
                    onCheckedChange = {
                        isSatelliteModeOn = !isSatelliteModeOn
                        if (isSatelliteModeOn) {
                            properties = properties.copy(mapType = MapType.SATELLITE)

                        } else {
                            properties = properties.copy(mapType = MapType.NORMAL)

                        }
                    }
                )
            }
        }
    }
}

