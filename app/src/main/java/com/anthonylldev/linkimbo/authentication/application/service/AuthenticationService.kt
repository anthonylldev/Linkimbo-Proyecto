package com.anthonylldev.linkimbo.authentication.application.service

import com.anthonylldev.linkimbo.authentication.application.dto.CreateAccountDto
import com.anthonylldev.linkimbo.authentication.application.dto.TokenDto

interface AuthenticationService {
    suspend fun createAccount(request: CreateAccountDto): TokenDto

    suspend fun authenticate()
}