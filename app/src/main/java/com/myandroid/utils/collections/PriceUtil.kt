package com.myandroid.utils.collections

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object PriceUtil {
    fun convertToRupiah(price: Double?): String {
        val symbol = "Rp. "
        val decimalFormat = DecimalFormat.getCurrencyInstance(Locale.ENGLISH) as DecimalFormat
        val formatRp = DecimalFormatSymbols()
        formatRp.currencySymbol = symbol
        formatRp.monetaryDecimalSeparator = ','
        formatRp.groupingSeparator = ','
        decimalFormat.decimalFormatSymbols = formatRp
        var converter = decimalFormat.format(price)
        converter = converter.substring(0, converter.length - 3)
        converter = converter.replace(",", ".")
        return converter
    }

    fun formatPrice(longData: Long): String {
        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.groupingSeparator = ','
        val decimalFormat = DecimalFormat()
        decimalFormat.decimalFormatSymbols = decimalFormatSymbols
        return decimalFormat.format(longData)
    }

    fun formatWithSymbol(price: Long?): String {
        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.groupingSeparator = '.'
        val decimalFormat = DecimalFormat()
        decimalFormat.decimalFormatSymbols = decimalFormatSymbols
        return "Rp. " + decimalFormat.format(price)
    }

    fun formatWithoutSymbol(price: Long?): String {
        val decimalFormatSymbols = DecimalFormatSymbols()
        decimalFormatSymbols.groupingSeparator = '.'
        val decimalFormat = DecimalFormat()
        decimalFormat.decimalFormatSymbols = decimalFormatSymbols
        return decimalFormat.format(price)
    }
}