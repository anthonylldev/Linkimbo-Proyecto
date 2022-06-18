package com.anthonylldev.linkimbo.activity.domain.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.activity.application.data.ActivityResponse
import com.anthonylldev.linkimbo.activity.application.service.ActivityService
import com.anthonylldev.linkimbo.post.application.data.PostResponse
import com.anthonylldev.linkimbo.util.ui.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val activityService: ActivityService
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _allActivities = mutableStateOf(emptyList<ActivityResponse>())
    val allActivities: State<List<ActivityResponse>> = _allActivities

    fun loadActivities() {
        viewModelScope.launch {
            _allActivities.value = activityService.loadActivities()

            _isLoading.value = false
            _eventFlow.emit(UiEvent.LoadSuccessful)
        }
    }
}