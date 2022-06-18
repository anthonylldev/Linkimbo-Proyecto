package com.anthonylldev.linkimbo.util.di

import com.anthonylldev.linkimbo.chat.application.service.MessageService
import com.anthonylldev.linkimbo.chat.application.service.impl.MessageServiceImpl
import com.anthonylldev.linkimbo.chat.infrastructure.MessageRestController
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
object ChatModule {

    @Provides
    @Singleton
    fun provideMessageApi(client: OkHttpClient): MessageRestController {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MessageRestController::class.java)
    }

    @Provides
    @Singleton
    fun provideMessageService(
        api: MessageRestController
    ): MessageService {
        return MessageServiceImpl(api)
    }

}