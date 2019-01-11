package com.myandroid.utils.collections

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.myandroid.utils.BaseApp

class KeyboardUtil {
    fun hideKeyboard(view: View?) {
        val imm = BaseApp.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun showKeyboard(view: View?) {
        val imm = BaseApp.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun hideKeyboardOnScroll(recyclerView: RecyclerView) {
        recyclerView.setOnTouchListener { _, _ ->
            hideKeyboard(recyclerView)
            return@setOnTouchListener false
        }
    }
}