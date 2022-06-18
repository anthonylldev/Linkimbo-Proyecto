package com.anthonylldev.linkimbo.util.di

import com.anthonylldev.linkimbo.authentication.infrastructure.AuthenticationRestController
import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.post.application.service.impl.PostServiceImpl
import com.anthonylldev.linkimbo.post.infrastructure.PostRestController
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
object PostModule {

    @Provides
    @Singleton
    fun providePostApi(client: OkHttpClient): PostRestController {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostRestController::class.java)
    }

    @Provides
    @Singleton
    fun providePostService(
        api: PostRestController
    ): PostService {
        return PostServiceImpl(api)
    }
}
