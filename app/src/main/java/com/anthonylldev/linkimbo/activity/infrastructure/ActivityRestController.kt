package com.anthonylldev.linkimbo.activity.infrastructure

import com.anthonylldev.linkimbo.activity.application.data.ActivityRequest
import com.anthonylldev.linkimbo.activity.application.data.ActivityResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ActivityRestController {

    @POST("/activity")
    suspend fun insertActivity(
        @Body activityRequest: ActivityRequest
    )

    @GET("/activity")
    suspend fun getActivities(): List<ActivityResponse>
}