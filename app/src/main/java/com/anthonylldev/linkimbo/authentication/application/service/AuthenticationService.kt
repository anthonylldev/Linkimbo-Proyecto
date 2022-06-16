package com.anthonylldev.linkimbo.authentication.application.service

import com.anthonylldev.linkimbo.authentication.application.data.CreateAccountRequest
import com.anthonylldev.linkimbo.authentication.application.data.LoginRequest
import com.anthonylldev.linkimbo.authentication.application.data.TokenResponse

interface AuthenticationService {
    suspend fun createAccount(request: CreateAccountRequest): TokenResponse

    suspend fun login(request: LoginRequest): TokenResponse

    suspend fun authenticate()
}