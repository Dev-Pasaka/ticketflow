package com.unbuniworks.camusat.efiber.domain.repository

import com.unbuniworks.camusat.efiber.data.remote.dto.getUserDto.GetUserDto
import com.unbuniworks.camusat.efiber.data.remote.dto.loginDto.LoginDto
import com.unbuniworks.camusat.efiber.data.remote.dto.resetPasswordDto.ResetPasswordDto
import com.unbuniworks.camusat.efiber.data.remote.dto.updatePasswordDto.UpdatePasswordDto
import com.unbuniworks.camusat.efiber.data.remote.model.ResetPasswordCredentials
import com.unbuniworks.camusat.efiber.data.remote.model.UserCredentials

interface UserRepository {
    suspend fun login(credentials: UserCredentials): LoginDto
    suspend fun getUserDto(userId:String, token: String): GetUserDto

    suspend fun resetPassword(resetPasswordCredentials: ResetPasswordCredentials):ResetPasswordDto
    suspend fun updatePassword(newPassword:String, token:String):UpdatePasswordDto
}