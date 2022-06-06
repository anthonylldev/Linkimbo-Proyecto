package com.anthonylldev.linkimbo.activity.domain.model

import com.anthonylldev.linkimbo.util.ui.presentation.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String
)
