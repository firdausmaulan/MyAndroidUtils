package com.myandroid.utils.collections

import android.view.View
import android.view.animation.AnimationUtils
import com.myandroid.utils.BaseApp

object AnimationUtil {
    fun start(view: View, animResource: Int) {
        val anim = AnimationUtils.loadAnimation(BaseApp.context, animResource)
        view.startAnimation(anim)
    }
}
