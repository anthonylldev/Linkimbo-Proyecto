package com.anthonylldev.linkimbo.authentication.application.dto

data class CreateAccountDto(
    val username: String,
    val email: String,
    val password: String
)
