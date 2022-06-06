package com.anthonylldev.linkimbo.authentication.application.service.impl

import android.content.SharedPreferences
import com.anthonylldev.linkimbo.authentication.application.service.AuthenticationService
import com.anthonylldev.linkimbo.authentication.application.dto.CreateAccountDto
import com.anthonylldev.linkimbo.authentication.application.dto.LoginDto
import com.anthonylldev.linkimbo.authentication.application.dto.TokenDto
import com.anthonylldev.linkimbo.authentication.infrastructure.AuthenticationRestController
import com.anthonylldev.linkimbo.util.Constants

class AuthenticationServiceImpl(
    private val api: AuthenticationRestController,
    private val sharedPreferences: SharedPreferences
) : AuthenticationService {

    override suspend fun createAccount(request: CreateAccountDto): TokenDto {
        try {
            val response = api.createAccount(request)

            response.token.let {
                println("Overriding token with $it")
                sharedPreferences.edit()
                    .putString(Constants.KEY_JWT_TOKEN, response.token)
                    .apply()
            }

            return response

        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun login(request: LoginDto): TokenDto {
        try {
            val response = api.login(request)

            response.token.let {
                println("Overriding token with $it")
                sharedPreferences.edit()
                    .putString(Constants.KEY_JWT_TOKEN, response.token)
                    .apply()
            }

            return response
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun authenticate() {
        return try {
            api.authenticate()
        } catch (e: Exception) {
            throw e
        }
    }
}