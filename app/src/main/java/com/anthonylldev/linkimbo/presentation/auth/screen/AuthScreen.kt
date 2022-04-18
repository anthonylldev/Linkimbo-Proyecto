package com.anthonylldev.linkimbo.presentation.auth.screen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.presentation.auth.screen.util.AuthPages
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun AuthScreen(
    navController: NavController
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val pagerSelect = rememberPagerState(AuthPages.values().size)

    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10),
        sheetState = sheetState,
        sheetContent = {
            AuthSheet(
                sheetState = sheetState,
                pagerSelect = pagerSelect,
                coroutineScope = coroutineScope
            )
        }
    ) {
        AuthContent(
            sheetState = sheetState,
            pagerSelect = pagerSelect,
            coroutineScope = coroutineScope
        )
    }
}