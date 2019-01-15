package com.myandroid.utils.collections

import com.myandroid.utils.BaseApp
import java.io.IOException

object AssetsUtil {
    fun loadJSONFromAsset(): String? {
        val json: String?
        try {
            val inputStream = BaseApp.context.assets.open("api_test.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}
