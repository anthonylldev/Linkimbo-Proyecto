package com.anthonylldev.linkimbo.domain.models

import com.anthonylldev.linkimbo.domain.util.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val timestamp: Long
)
