package com.anthonylldev.linkimbo.activity.application.data

data class ActivityRequest(
    val userId: String,
    val actionType: String,
    val timestamp: Long
)
