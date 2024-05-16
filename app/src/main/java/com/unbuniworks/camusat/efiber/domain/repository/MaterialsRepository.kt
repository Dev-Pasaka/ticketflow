package com.unbuniworks.camusat.efiber.domain.repository

import android.app.Activity
import com.unbuniworks.camusat.efiber.data.remote.dto.dispatchProjectDto.DispatchProjectDto
import com.unbuniworks.camusat.efiber.data.remote.dto.materialDto.MaterialsDtoItem

interface MaterialsRepository {

    suspend fun getMaterials(token:String):MaterialsDtoItem
    suspend fun getDispatchProject(token: String):DispatchProjectDto
}