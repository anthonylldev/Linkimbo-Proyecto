package com.anthonylldev.linkimbo.domain.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtil {
    fun timestamToFormattedString(timestamp: Long, patter: String): String {
        return SimpleDateFormat(patter, Locale.getDefault()).run {
            format(timestamp)
        }
    }
}