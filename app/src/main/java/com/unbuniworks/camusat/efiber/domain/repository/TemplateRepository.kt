package com.unbuniworks.camusat.efiber.domain.repository

import com.unbuniworks.camusat.efiber.data.remote.dto.templatesDto.TemplatesDtoItem

interface TemplateRepository {
    suspend fun getTemplates():List<TemplatesDtoItem>
}