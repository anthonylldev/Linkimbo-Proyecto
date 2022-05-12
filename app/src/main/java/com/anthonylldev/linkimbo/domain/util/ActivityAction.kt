package com.anthonylldev.linkimbo.domain.util

sealed class ActivityAction {
    object LikedPost: ActivityAction()
    object CommentOnPost: ActivityAction()
}
