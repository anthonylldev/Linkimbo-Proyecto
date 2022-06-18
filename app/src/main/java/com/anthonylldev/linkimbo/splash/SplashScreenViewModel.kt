package com.anthonylldev.linkimbo.splash

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.authentication.application.service.AuthenticationService
import com.anthonylldev.linkimbo.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val authenticationService: AuthenticationService
) : ViewModel() {

    fun checkSession(): Boolean {
        if (!checkSharedPreferences()) return false


        viewModelScope.launch {
            try {
                authenticationService.authenticate()
            } catch (e: Exception) {
                removePreferences()
            }
        }

        return true
    }

    private fun removePreferences() {
        sharedPreferences.edit().clear().apply()
    }

    private fun checkSharedPreferences(): Boolean {
        val jwt = sharedPreferences.getString(Constants.KEY_JWT_TOKEN, null)
        val userId = sharedPreferences.getString(Constants.PERSONAL_USER_ID, null)

        return jwt != null && userId != null
    }
}