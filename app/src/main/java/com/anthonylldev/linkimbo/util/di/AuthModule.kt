package com.anthonylldev.linkimbo.util.di

import android.content.SharedPreferences
import com.anthonylldev.linkimbo.authentication.application.service.AuthenticationService
import com.anthonylldev.linkimbo.authentication.application.service.impl.AuthenticationServiceImpl
import com.anthonylldev.linkimbo.authentication.infrastructure.AuthenticationRestController
import com.anthonylldev.linkimbo.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(client: OkHttpClient): AuthenticationRestController {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthenticationRestController::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthService(
        api: AuthenticationRestController,
        sharedPreferences: SharedPreferences
    ): AuthenticationService {
        return AuthenticationServiceImpl(api, sharedPreferences)
    }
}