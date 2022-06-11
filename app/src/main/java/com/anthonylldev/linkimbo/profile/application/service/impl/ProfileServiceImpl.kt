package com.anthonylldev.linkimbo.profile.application.service.impl

import android.content.SharedPreferences
import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.authentication.infrastructure.AuthenticationRestController
import com.anthonylldev.linkimbo.profile.application.service.ProfileService
import com.anthonylldev.linkimbo.profile.domain.model.ProfileResponse
import com.anthonylldev.linkimbo.profile.infrastructure.ProfileRestController
import com.anthonylldev.linkimbo.util.Constants

class ProfileServiceImpl(
    private val api: ProfileRestController,
    private val sharedPreferences: SharedPreferences
) : ProfileService {

    override suspend fun loadProfile(
        userId: String
    ): ProfileResponse? {
        try {

            if (userId == "me") {
                this.sharedPreferences.getString(Constants.PERSONAL_USER_ID, null)?.let {
                    return this.api.loadProfile(it)
                }
            } else {
                return this.api.loadProfile(userId)
            }

            return null
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getUser(): User {
        try {
            return this.api.getUser()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun updateUser(user: User) {
        try {
            this.api.updateUser(user)
        } catch (e: Exception) {
            throw e
        }
    }
}