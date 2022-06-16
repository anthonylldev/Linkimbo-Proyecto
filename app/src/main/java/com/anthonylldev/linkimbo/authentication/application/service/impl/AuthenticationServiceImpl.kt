package com.anthonylldev.linkimbo.authentication.application.service.impl

import android.content.SharedPreferences
import com.anthonylldev.linkimbo.authentication.application.service.AuthenticationService
import com.anthonylldev.linkimbo.authentication.application.data.CreateAccountRequest
import com.anthonylldev.linkimbo.authentication.application.data.LoginRequest
import com.anthonylldev.linkimbo.authentication.application.data.TokenResponse
import com.anthonylldev.linkimbo.authentication.infrastructure.AuthenticationRestController
import com.anthonylldev.linkimbo.util.Constants

class AuthenticationServiceImpl(
    private val api: AuthenticationRestController,
    private val sharedPreferences: SharedPreferences
) : AuthenticationService {

    override suspend fun createAccount(request: CreateAccountRequest): TokenResponse {
        try {
            val response = api.createAccount(request)

            response.userId.let {
                println("Overriding userId with $it")
                sharedPreferences.edit()
                    .putString(Constants.PERSONAL_USER_ID, response.userId)
                    .apply()
            }

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

    override suspend fun login(request: LoginRequest): TokenResponse {
        try {
            val response = api.login(request)

            response.userId.let {
                println("Overriding userId with $it")
                sharedPreferences.edit()
                    .putString(Constants.PERSONAL_USER_ID, response.userId)
                    .apply()
            }

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