package com.myandroid.utils.collections

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun FragmentActivity.addFragment(fragment: Fragment, layout: Int) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.setCustomAnimations(
        android.R.animator.fade_in,
        android.R.animator.fade_out
    )
    fragmentTransaction.add(layout, fragment, fragment.tag)
    fragmentTransaction.commit()
}

fun FragmentActivity.replaceFragment(fragment: Fragment, layout: Int) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.setCustomAnimations(
        android.R.animator.fade_in,
        android.R.animator.fade_out
    )
    fragmentTransaction.replace(layout, fragment, fragment.tag)
    fragmentTransaction.commit()
}