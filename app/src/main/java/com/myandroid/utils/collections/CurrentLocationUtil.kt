package com.myandroid.utils.collections

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle


import java.util.Locale

import android.content.Context.LOCATION_SERVICE
import com.myandroid.utils.collections.PermissionUtil.checkAppPermission
import com.myandroid.utils.model.CurrentLocation

object CurrentLocationUtil {

    fun Activity.getCurrentLocation(): CurrentLocation {
        val lm = getSystemService(LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)

        // getting network status
        val isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        val locationListener = locationListener()

        // Check permission
        if (!checkAppPermission(ACCESS_FINE_LOCATION, 0) ||
            !checkAppPermission(ACCESS_COARSE_LOCATION, 1)
        ) {
            return CurrentLocation()
        }

        val location: Location?
        if (isNetworkEnabled) { // if Network Enabled get lat/long using Network Provider
            lm.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000, 50.0f, locationListener
            )
            location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            return setLocation(location)
        } else if (isGPSEnabled) { // if GPS Enabled get lat/long using GPS Provider
            lm.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 1000, 50.0f, locationListener
            )
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            return setLocation(location)
        }

        return CurrentLocation()
    }

    private fun locationListener(): LocationListener {
        return object : LocationListener {
            override fun onLocationChanged(location: Location) {

            }

            override fun onStatusChanged(s: String, i: Int, bundle: Bundle) {

            }

            override fun onProviderEnabled(s: String) {

            }

            override fun onProviderDisabled(s: String) {

            }
        }
    }

    private fun Activity.setLocation(location: Location?): CurrentLocation {
        if (location != null) {
            try {
                val latitude = location.latitude
                val longitude = location.longitude
                val geoCoder = Geocoder(this, Locale.getDefault())
                val addresses: List<Address>
                addresses = geoCoder.getFromLocation(latitude, longitude, 1)

                val currentLocation = CurrentLocation()
                currentLocation.name = addresses[0].getAddressLine(0)
                currentLocation.latitude = latitude
                currentLocation.longitude = longitude

                return currentLocation
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return CurrentLocation()
    }
}
