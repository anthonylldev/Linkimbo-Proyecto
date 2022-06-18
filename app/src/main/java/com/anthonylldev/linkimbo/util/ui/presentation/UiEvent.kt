package com.anthonylldev.linkimbo.util.ui.presentation

abstract class Event

sealed class UiEvent: Event() {
    object Like: UiEvent()
    object Comment: UiEvent()
    object LoadSuccessful: UiEvent()
}
