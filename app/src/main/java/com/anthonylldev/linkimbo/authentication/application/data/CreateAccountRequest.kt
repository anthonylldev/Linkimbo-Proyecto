package com.anthonylldev.linkimbo.authentication.application.data

data class CreateAccountRequest(
    val username: String,
    val email: String,
    val password: String
)
