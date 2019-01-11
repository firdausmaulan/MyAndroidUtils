package com.myandroid.utils.collections

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference

class InputPriceUtil(editText: EditText) : TextWatcher {

    var mMaxLength = 20

    private val editTextWeakReference: WeakReference<EditText> = WeakReference(editText)

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun afterTextChanged(editable: Editable) {
        try {
            val editText = editTextWeakReference.get() ?: return
            val stringData = editable.toString()
            if (stringData.length <= mMaxLength) {
                editText.removeTextChangedListener(this)
                var cleanString = stringData.replace("[,.]".toRegex(), "")
                cleanString = if (TextUtils.isEmpty(cleanString)) "" else cleanString
                if (cleanString.isEmpty()) {
                    editText.setText("")
                } else {
                    val longData = cleanString.toLong()
                    val formattedPrice = PriceUtil.formatPrice(longData)
                    editText.setText(formattedPrice)
                    // Move cursor to end of string
                    editText.setSelection(formattedPrice.length)
                }
                editText.addTextChangedListener(this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}