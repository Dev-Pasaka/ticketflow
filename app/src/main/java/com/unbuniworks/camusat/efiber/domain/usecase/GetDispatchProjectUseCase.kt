package com.unbuniworks.camusat.efiber.domain.usecase

import android.app.Activity
import android.util.Log
import com.unbuniworks.camusat.efiber.common.Constants
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepository
import com.unbuniworks.camusat.efiber.data.local.sharedPreference.SharedPreferenceRepositoryImpl
import com.unbuniworks.camusat.efiber.data.remote.dto.dispatchProjectDto.toFilterMaterials
import com.unbuniworks.camusat.efiber.data.remote.dto.materialDto.toFilterMaterials
import com.unbuniworks.camusat.efiber.data.repository.MaterialRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.model.Project
import com.unbuniworks.camusat.efiber.domain.repository.MaterialsRepository

class GetDispatchProjectUseCase(
    private val repository: MaterialsRepository = MaterialRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {

    suspend fun getProjects(activity: Activity): List<Project> =
        try {
            val token = sharedPreferenceRepository.getString(Constants.token, activity)?: ""
            val response = repository.getDispatchProject(token)
            val materials = response.toFilterMaterials()
            materials
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.e("Projects", it) }
            emptyList()
        }

}