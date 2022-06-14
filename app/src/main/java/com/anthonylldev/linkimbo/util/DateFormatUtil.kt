package com.anthonylldev.linkimbo.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtil {
    fun timestampToFormattedString(timestamp: Long, patter: String): String {
        return SimpleDateFormat(patter, Locale.getDefault()).run {
            format(timestamp)
        }
    }
}