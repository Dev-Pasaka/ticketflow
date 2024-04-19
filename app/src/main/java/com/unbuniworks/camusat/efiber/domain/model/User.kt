package com.unbuniworks.camusat.efiber.domain.model

data class User(
    val status:Boolean,
    val passwordSet:String,
    val email:String,
    val firstName:String,
    val surname:String,
    val mobile:String,
)