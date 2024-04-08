package com.unbuniworks.camusat.efiber.common

import android.os.Build
import android.text.format.DateUtils.getDayOfWeekString
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


object Utils {

    fun getColor(colorString: String): Color {
        val cleanedColorString = cleanAndValidateColorString(colorString)
        return Color(android.graphics.Color.parseColor(cleanedColorString))
    }

    private fun cleanAndValidateColorString(colorString: String): String {
        var cleanedColorString = colorString.trim()
        if (!cleanedColorString.startsWith("#")) {
            cleanedColorString = "#$cleanedColorString" // Add '#' prefix if missing
        }
        if (cleanedColorString.length == 4) {
            // If the color string is of format #RGB, convert it to #RRGGBB
            val r = cleanedColorString[1]
            val g = cleanedColorString[2]
            val b = cleanedColorString[3]
            cleanedColorString = "#$r$r$g$g$b$b"
        }
        return cleanedColorString
    }

    fun getCurrentFormattedDayOfWeek(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE /dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(calendar.time)
        return formattedDate
    }



}