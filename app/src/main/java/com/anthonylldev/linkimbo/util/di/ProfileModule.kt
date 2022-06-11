package com.anthonylldev.linkimbo.util.di

import android.content.SharedPreferences
import com.anthonylldev.linkimbo.profile.application.service.ProfileService
import com.anthonylldev.linkimbo.profile.application.service.impl.ProfileServiceImpl
import com.anthonylldev.linkimbo.profile.infrastructure.ProfileRestController
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
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileApi(client: OkHttpClient): ProfileRestController {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfileRestController::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileService(
        api: ProfileRestController,
        sharedPreferences: SharedPreferences
    ): ProfileService {
        return ProfileServiceImpl(api, sharedPreferences)
    }

}