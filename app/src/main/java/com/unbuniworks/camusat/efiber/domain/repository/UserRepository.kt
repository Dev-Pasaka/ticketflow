package com.unbuniworks.camusat.efiber.domain.repository

import com.unbuniworks.camusat.efiber.data.remote.dto.loginDto.LoginDto
import com.unbuniworks.camusat.efiber.data.remote.model.UserCredentials

interface UserRepository {
    suspend fun login(credentials: UserCredentials): LoginDto
}