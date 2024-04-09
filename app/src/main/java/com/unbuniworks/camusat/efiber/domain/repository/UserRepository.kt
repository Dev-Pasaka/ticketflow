package com.unbuniworks.camusat.efiber.domain.repository

import com.unbuniworks.camusat.efiber.data.remote.dto.getUserDto.GetUserDto
import com.unbuniworks.camusat.efiber.data.remote.dto.loginDto.LoginDto
import com.unbuniworks.camusat.efiber.data.remote.model.UserCredentials

interface UserRepository {
    suspend fun login(credentials: UserCredentials): LoginDto
    suspend fun getUserDto(userId:String): GetUserDto
}