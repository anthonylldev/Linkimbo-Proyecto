package com.anthonylldev.linkimbo.presentation.auth.screen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.presentation.auth.AuthViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10),
        sheetState = viewModel.sheetState.value,
        sheetContent = {
            AuthSheet(
                coroutineScope = coroutineScope,
                navController = navController,
            )
        }
    ) {
        AuthContent(
            coroutineScope = coroutineScope
        )
    }
}