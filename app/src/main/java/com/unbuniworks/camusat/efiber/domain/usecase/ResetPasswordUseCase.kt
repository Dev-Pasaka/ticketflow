package com.unbuniworks.camusat.efiber.domain.usecase

import com.unbuniworks.camusat.efiber.data.remote.dto.resetPasswordDto.ResetPasswordDto
import com.unbuniworks.camusat.efiber.data.remote.model.ResetPasswordCredentials
import com.unbuniworks.camusat.efiber.data.repository.UserRepositoryImpl
import com.unbuniworks.camusat.efiber.domain.repository.UserRepository

class ResetPasswordUseCase(
    private val repository: UserRepository = UserRepositoryImpl()
) {
    suspend fun resetPassword(resetPasswordCredentials: ResetPasswordCredentials):ResetPasswordDto{
        return try {
            repository.resetPassword(resetPasswordCredentials)
        }catch (e:Exception){
            ResetPasswordDto(status = false, message = e.localizedMessage ?: "")
        }
    }
}