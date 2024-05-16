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
        val localTimeZone: TimeZone = TimeZone.getDefault()
        val localTimeZoneAbbreviation: String = localTimeZone.getDisplayName(false, TimeZone.SHORT)

        inputDateFormat.timeZone = TimeZone.getTimeZone(localTimeZoneAbbreviation)

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


    @RequiresApi(Build.VERSION_CODES.O)
    fun formatTimestampInLocalTime(timestamp: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault() // Using the default local time zone

        val date: Date = inputFormat.parse(timestamp)

        return outputFormat.format(date)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatIsoTime(dateTimeString: String): String {
        // Parse the timestamp into Instant
        val instant = Instant.parse(dateTimeString)

        // Get the device's default time zone
        val defaultZoneId = ZoneId.systemDefault()

        // Convert Instant to ZonedDateTime in the device's default time zone
        val localDateTime = ZonedDateTime.ofInstant(instant, defaultZoneId)

        // Format the ZonedDateTime object into a user-readable string
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return localDateTime.format(formatter)
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
