package com.myandroid.utils

import android.content.Intent
import android.net.Uri


fun MainActivity.openNewsOnBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}
