package com.anthonylldev.linkimbo.post.domain.presentation

abstract class Event

sealed class PostEvent: Event() {
    object Init: PostEvent()
    object Like: PostEvent()
}
