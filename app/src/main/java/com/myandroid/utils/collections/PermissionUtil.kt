package com.myandroid.utils.collections

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

object PermissionUtil {

    fun Activity.checkAppPermission(permission: String, requestCode: Int): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            return false
        }

        return true
    }

    // TODO add google map to your gradle file
    /*fun Activity.permissionLocation(map: GoogleMap, requestCode: Int): Boolean {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            return false
        } else {
            map.isMyLocationEnabled = true
            return true
        }
    }*/
}