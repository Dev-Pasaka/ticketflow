package com.unbuniworks.camusat.efiber.presentation.ui.screens.ticketInformation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest



class LocationHelper(private val context: Context) {

    private val locationManager: LocationManager? =
        context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager

    private val locationListener: LocationListener =
        LocationListener { location ->
            val latitude = location.latitude
            val longitude = location.longitude
            Log.i("test", "Latitude: $latitude ; Longitude: $longitude")
        }


}