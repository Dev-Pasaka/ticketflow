package com.unbuniworks.camusat.efiber.common

import android.os.Build
import android.text.format.DateUtils.getDayOfWeekString
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.unbuniworks.camusat.efiber.data.remote.httpClient.HttpClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.net.URL
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*


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

    fun convertDateStringToDate(inputDateString: String): String {
        // Define the date format
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

        // Set the time zone to UTC
        inputDateFormat.timeZone = TimeZone.getTimeZone("UTC")

        // Parse the string to obtain a Date object
        val date: Date = inputDateFormat.parse(inputDateString) ?: Date()

        // Format the date with 24-hour clock system
        val outputDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return outputDateFormat.format(date)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertUtcToLocal(utcDateTime: String?): String {
        utcDateTime?.let {
            if (it.isNotEmpty()) {
                try {
                    val utcInstant = Instant.parse(utcDateTime)
                    val localDateTime = ZonedDateTime.ofInstant(utcInstant, ZoneId.systemDefault())
                    val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")
                    return localDateTime.format(formatter)
                } catch (e: DateTimeParseException) {
                    e.printStackTrace()
                    return "Error parsing date: $utcDateTime"
                }
            }
        }
        return ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun categorizeDate(utcDateTime: String?): String {
        utcDateTime?.let {
            if (it.isNotEmpty()) {
                try {
                    val utcInstant = Instant.parse(utcDateTime)
                    val localDateTime = LocalDateTime.ofInstant(utcInstant, ZoneId.systemDefault())
                    val today = LocalDate.now()
                    val inputDate = localDateTime.toLocalDate()

                    return when {
                        inputDate == today -> "Today"
                        inputDate == today.minusDays(1) -> "Yesterday"
                        else -> inputDate.format(DateTimeFormatter.ofPattern("dd MMMM, yyyy"))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    return "Error parsing date: $utcDateTime"
                }
            }
        }
        return ""
    }


    fun formatIsoDateTime(dateTimeString: String): String {
        // Split the date-time string into its components
        val parts = dateTimeString.split("[-T:.Z]".toRegex())

        // Extract the components
        val year = parts[0].toInt()
        val month = parts[1].toInt()
        val day = parts[2].toInt()
        val hour = parts[3].toInt()
        val minute = parts[4].toInt()
        val second = parts[5].toInt()

        // Convert the hour to 12-hour clock system and determine AM/PM
        val formattedHour = if (hour == 0) 12 else if (hour <= 12) hour else hour - 12
        val amPm = if (hour < 12) "AM" else "PM"

        // Format the date and time components into a user-readable string
        return String.format("%04d-%02d-%02d  %02d:%02d $amPm", year, month, day, formattedHour, minute)
    }

    fun extractCoordinates(pairString: String): Pair<Double, Double>? {
        // Remove parentheses and split the string by comma
        val parts = pairString.replace("(", "").replace(")", "").split(",")

        // Ensure there are two parts (latitude and longitude)
        if (parts.size != 2) {
            return null
        }

        // Extract latitude and longitude as Doubles
        val latitude = parts[0].trim().toDoubleOrNull()
        val longitude = parts[1].trim().toDoubleOrNull()

        // Return the pair if both latitude and longitude are not null
        return if (latitude != null && longitude != null) {
            Pair(latitude, longitude)
        } else {
            null
        }
    }

    suspend fun getLocationName(latitude: Double, longitude: Double): String? {
        val url = "https://nominatim.openstreetmap.org/reverse?format=json&lat=$latitude&lon=$longitude"

        return try {

            // Make the GET request
            val response = HttpClient.client.get(url).body<String>()

            // Parse the JSON response to extract the location name
            val regex = "\"display_name\"\\s*:\\s*\"([^\"]+)\"".toRegex()
            val matchResult = regex.find(response)
            matchResult?.groupValues?.get(1)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}
