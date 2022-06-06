package com.anthonylldev.linkimbo.util.ui.presentation

sealed class ActivityAction {
    object LikedPost: ActivityAction()
    object CommentOnPost: ActivityAction()
}
