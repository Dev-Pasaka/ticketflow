package com.unbuniworks.camusat.efiber.domain.usecase

class ValidateResetPasswordUseCase() {
    fun validateResetPassword(password: String): Pair<Boolean, String> {
        // Check if password length is at least 8 characters
        if (password.length < 8) {
            return Pair(false, "Password must be at least 8 characters long")
        }

        // Check if password includes at least 1 symbol, 1 uppercase letter, 1 lowercase letter, and 1 number
        val hasSymbol = password.any { it.isLetterOrDigit().not() }
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasNumber = password.any { it.isDigit() }

        if (!(hasSymbol && hasUpperCase && hasLowerCase && hasNumber)) {
            return Pair(false, "1 symbol, 1 uppercase letter, 1 lowercase letter, and 1 number")
        }

        // If all checks passed, return true
        return Pair(true, "Password is valid")
    }
}
