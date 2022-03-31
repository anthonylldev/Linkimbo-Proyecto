package com.anthonylldev.linkimbo.presentation.auth

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.presentation.auth.components.*

@OptIn(ExperimentalMaterialApi::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun AuthScreen(
    navController: NavController
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10),
        sheetState = sheetState,
        sheetContent = { AuthSheet(coroutineScope = coroutineScope) }
    ) {
        AuthContent(
            sheetState = sheetState,
            coroutineScope = coroutineScope
        )
    }
}