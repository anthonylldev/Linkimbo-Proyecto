package com.anthonylldev.linkimbo.authentication.infrastructure

import com.anthonylldev.linkimbo.authentication.application.dto.CreateAccountDto
import com.anthonylldev.linkimbo.authentication.application.dto.LoginDto
import com.anthonylldev.linkimbo.authentication.application.dto.TokenDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationRestController {

    @POST("/createAccount")
    suspend fun createAccount(
        @Body createAccountDto: CreateAccountDto
    ): TokenDto

    @POST("/login")
    suspend fun login(
        @Body loginDto: LoginDto
    ): TokenDto

    @GET("/authenticate")
    suspend fun authenticate()
}