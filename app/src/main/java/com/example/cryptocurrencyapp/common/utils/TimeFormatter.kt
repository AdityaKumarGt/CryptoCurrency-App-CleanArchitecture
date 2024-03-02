package com.example.cryptocurrencyapp.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object TimeFormatter {
    @RequiresApi(Build.VERSION_CODES.O)
    fun intoISTFormat(inputTimeString: String): String {
        val instant = Instant.parse(inputTimeString)
        val istDateTime = instant.atZone(ZoneId.of("Asia/Kolkata"))
        val istDate = istDateTime.toLocalDate()
        return istDate.toString()
    }
}