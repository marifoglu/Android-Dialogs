package com.darth.alert_dialogs.util

import android.content.Context
import java.io.IOException

class JSONUtil {
    fun getJsonData(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}