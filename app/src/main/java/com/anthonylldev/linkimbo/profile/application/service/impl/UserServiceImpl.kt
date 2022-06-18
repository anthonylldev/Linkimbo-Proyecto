package com.anthonylldev.linkimbo.profile.application.service.impl

import android.content.SharedPreferences
import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.profile.application.data.ProfilePostResponse
import com.anthonylldev.linkimbo.profile.application.service.UserService
import com.anthonylldev.linkimbo.profile.application.data.ProfileResponse
import com.anthonylldev.linkimbo.profile.infrastructure.UserRestController
import com.anthonylldev.linkimbo.util.Constants

class UserServiceImpl(
    private val api: UserRestController,
    private val sharedPreferences: SharedPreferences
) : UserService {

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

    override suspend fun loadPosts(userId: String): List<ProfilePostResponse> {
        try {
            if (userId == "me") {
                this.sharedPreferences.getString(Constants.PERSONAL_USER_ID, null)?.let {
                    return this.api.loadPosts(it)
                }
            } else {
                return this.api.loadPosts(userId)
            }

            return emptyList()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getUserById(userId: String): User {
        try {
            return this.api.getUserById(userId)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun updateUserById(user: User, userId: String) {
        try {
            this.api.updateUserById(user, userId)
        } catch (e: Exception) {
            throw e
        }
    }
}