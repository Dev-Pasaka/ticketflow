package com.unbuniworks.camusat.efiber.common

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import java.util.*


object LanguageManager {
    fun setLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, resources.displayMetrics)
    }
}