package com.myandroid.utils.collections

import android.app.Activity
import android.os.Build
import java.util.*

object LocaleUtil {

    fun Activity.initLanguage() {
        // TODO change language code value
        val languageCode = "en"
        //val languageCode = PreferenceHelper().language
        val res = resources
        val dm = res.displayMetrics
        val config = res.configuration

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.locale = Locale(languageCode)
            res.updateConfiguration(config, dm)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(Locale(languageCode))
            createConfigurationContext(config)
        }
    }
}