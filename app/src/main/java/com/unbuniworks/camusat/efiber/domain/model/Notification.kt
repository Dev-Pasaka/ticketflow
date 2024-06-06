package com.unbuniworks.camusat.efiber.domain.model

data class Notification(
    val id: String,
    val title: String,
    val description: String,
    val status: String,
    val createdAt: String,
)