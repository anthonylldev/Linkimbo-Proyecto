package com.anthonylldev.linkimbo.presentation.auth.screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun AuthSheet(
    sheetState: ModalBottomSheetState,
    pagerSelect: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .height(475.dp)
            .fillMaxWidth(),
        backgroundColor = Color.White,
        topBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Button(
                    modifier = Modifier
                        .height(6.dp)
                        .width(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.LightGray
                    ),
                    onClick = {
                        coroutineScope.launch {
                            sheetState.animateTo(ModalBottomSheetValue.Hidden, tween(500))
                        }
                    }
                ) {}
            }
        }
    ) {
        HorizontalPager(state = pagerSelect) { index ->
            when (index) {
                0 -> {
                    AuthCreateAccount(navController = navController)
                }
                1 -> {
                    AuthLogin(navController = navController)
                }
            }
        }
    }
}