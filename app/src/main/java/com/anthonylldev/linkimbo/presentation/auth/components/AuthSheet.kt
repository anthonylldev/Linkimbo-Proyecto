package com.anthonylldev.linkimbo.presentation.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AuthSheet(
    coroutineScope: CoroutineScope
) {

    val pagerSelect = rememberPagerState(TabPages.values().size)

    Scaffold(
        modifier = Modifier
            .height(500.dp)
            .fillMaxWidth(),
        topBar = {
            TabScreen(selectIndex = pagerSelect.currentPage, onSelect = {
                coroutineScope.launch {
                    pagerSelect.animateScrollToPage(it.ordinal)
                }
            })
        }
    ) {
        HorizontalPager(state = pagerSelect) { index ->
            when (index) {
                0 -> { AuthCreateAccount() }
                1 -> { AuthLogin() }
            }
        }
    }
}