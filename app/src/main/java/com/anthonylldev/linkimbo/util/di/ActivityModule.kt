package com.anthonylldev.linkimbo.util.di

import com.anthonylldev.linkimbo.activity.application.service.ActivityService
import com.anthonylldev.linkimbo.activity.application.service.impl.ActivityServiceImpl
import com.anthonylldev.linkimbo.activity.infrastructure.ActivityRestController
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
object ActivityModule {

    @Provides
    @Singleton
    fun provideActivityApi(client: OkHttpClient): ActivityRestController {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ActivityRestController::class.java)
    }

    @Provides
    @Singleton
    fun provideActivityService(
        api: ActivityRestController,
    ): ActivityService {
        return ActivityServiceImpl(api)
    }
}