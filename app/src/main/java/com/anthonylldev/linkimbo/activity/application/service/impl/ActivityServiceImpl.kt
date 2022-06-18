package com.anthonylldev.linkimbo.activity.application.service.impl

import com.anthonylldev.linkimbo.activity.application.data.ActivityRequest
import com.anthonylldev.linkimbo.activity.application.data.ActivityResponse
import com.anthonylldev.linkimbo.activity.application.service.ActivityService
import com.anthonylldev.linkimbo.activity.infrastructure.ActivityRestController

class ActivityServiceImpl(
    private val api: ActivityRestController
) : ActivityService {

    override suspend fun loadActivities(): List<ActivityResponse> {
        try {
           return this.api.getActivities()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun insertActivity(request: ActivityRequest) {

        try {
            this.api.insertActivity(request)
        } catch (e: Exception) {
            throw e
        }
    }
}