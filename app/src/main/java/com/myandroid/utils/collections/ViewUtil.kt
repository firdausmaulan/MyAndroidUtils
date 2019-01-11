package com.myandroid.utils.collections

import android.view.View
import android.view.ViewGroup


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun nullParent(): ViewGroup? {
    return null
}