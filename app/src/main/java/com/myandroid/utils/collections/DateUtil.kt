package com.myandroid.utils.collections

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun formatDate(strDate: String?): String {
        if (strDate.isNullOrEmpty()) return ""
        val date = SimpleDateFormat("dd/MM/yy HH:mm:ssZ", Locale.ENGLISH).parse(strDate)
        return SimpleDateFormat("EEEE, dd MMM yyyy\n\nHH:mm", Locale.getDefault()).format(date)
    }

    fun convertDateToTimeMillis(strDate: String?): Long {
        if (strDate.isNullOrEmpty()) return 0L
        val date = SimpleDateFormat("dd/MM/yy HH:mm:ssXXX", Locale.ENGLISH).parse(strDate)
        return date.time
    }

    fun getCurrentDateTime(): String {
        val calendar = Calendar.getInstance().time
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        return df.format(calendar)
    }

    fun getCurrentDate(): String {
        val calendar = Calendar.getInstance().time
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return df.format(calendar)
    }

    fun getMonthName(month: Int?): String {
        when (month) {
            1 -> {
                return "Januari"
            }
            2 -> {
                return "Februari"
            }
            3 -> {
                return "Maret"
            }
            4 -> {
                return "April"
            }
            5 -> {
                return "Mei"
            }
            6 -> {
                return "Juni"
            }
            7 -> {
                return "Juli"
            }
            8 -> {
                return "Agustus"
            }
            9 -> {
                return "September"
            }
            10 -> {
                return "Oktober"
            }
            11 -> {
                return "November"
            }
            12 -> {
                return "Desember"
            }
            else -> {
                return ""
            }
        }
    }
}