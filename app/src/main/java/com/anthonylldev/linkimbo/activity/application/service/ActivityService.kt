package com.anthonylldev.linkimbo.activity.application.service

import com.anthonylldev.linkimbo.activity.application.data.ActivityRequest
import com.anthonylldev.linkimbo.activity.application.data.ActivityResponse

interface ActivityService {

    suspend fun loadActivities(): List<ActivityResponse>
    suspend fun insertActivity(request: ActivityRequest)
}