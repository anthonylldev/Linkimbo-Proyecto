package com.anthonylldev.linkimbo.util

abstract class Event

sealed class UiEvent: Event() {
    object Like: UiEvent()
    object Comment: UiEvent()
    object LoadSuccesful: UiEvent()
}
