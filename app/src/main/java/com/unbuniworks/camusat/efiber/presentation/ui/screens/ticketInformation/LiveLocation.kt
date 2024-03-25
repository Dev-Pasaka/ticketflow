package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient

//class LocationManager(private val context: Context) {
//
//    private val fusedLocationClient: FusedLocationProviderClient =
//        LocationServices.getFusedLocationProviderClient(context)
//
//    private val locationRequest: LocationRequest = LocationRequest.create().apply {
//        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        interval = 1000 // Update interval in milliseconds
//    }
//
//    @SuppressLint("MissingPermission")
//    fun startLocationUpdates(locationCallback: (Location) -> Unit) {
//        val callback = object : LocationCallback() {
//            override fun onLocationResult(result: LocationResult?) {
//                result?.lastLocation?.let {
//                    // Handle live location update
//                    locationCallback.invoke(it)
//                }
//            }
//        }
//
//        fusedLocationClient.requestLocationUpdates(locationRequest, callback, null)
//    }
//
//    fun stopLocationUpdates() {
//        // Stop receiving location updates
//        fusedLocationClient.removeLocationUpdates(null)
//    }
//}