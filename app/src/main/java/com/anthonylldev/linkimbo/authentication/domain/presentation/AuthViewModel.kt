package com.anthonylldev.linkimbo.authentication.domain.presentation

import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.anthonylldev.linkimbo.authentication.domain.presentation.screen.util.AuthPages
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val _sheetState = mutableStateOf(ModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    ))
    val sheetState: State<ModalBottomSheetState> = _sheetState

    private val _pagerState = mutableStateOf(PagerState(AuthPages.values().size))
    val pagerState: State<PagerState> = _pagerState


    suspend fun sheetAnimateTo(value: ModalBottomSheetValue, tween: Int) {
        _sheetState.value.animateTo(value, tween(tween))
    }

    suspend fun scrollPage(page: AuthPages) {
        _pagerState.value.animateScrollToPage(page = page.ordinal)
    }

}