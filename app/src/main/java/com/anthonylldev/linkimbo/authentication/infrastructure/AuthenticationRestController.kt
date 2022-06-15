package com.anthonylldev.linkimbo.authentication.infrastructure

import com.anthonylldev.linkimbo.authentication.application.data.CreateAccountRequest
import com.anthonylldev.linkimbo.authentication.application.data.LoginRequest
import com.anthonylldev.linkimbo.authentication.application.data.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationRestController {

    @POST("/createAccount")
    suspend fun createAccount(
        @Body createAccountRequest: CreateAccountRequest
    ): TokenResponse

    @POST("/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): TokenResponse

    @GET("/authenticate")
    suspend fun authenticate()
}