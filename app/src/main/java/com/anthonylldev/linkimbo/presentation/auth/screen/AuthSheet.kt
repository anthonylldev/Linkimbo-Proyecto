package com.anthonylldev.linkimbo.presentation.auth.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.presentation.auth.AuthViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalPagerApi::class, ExperimentalMaterialApi::class,
    androidx.compose.ui.ExperimentalComposeUiApi::class
)
@Composable
fun AuthSheet(
    coroutineScope: CoroutineScope,
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier
            .height(500.dp)
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
                            keyboardController?.hide()
                            viewModel.sheetAnimateTo(ModalBottomSheetValue.Hidden, 500)
                        }
                    }
                ) {}
            }
        }
    ) {
        HorizontalPager(state = viewModel.pagerState.value) { index ->
            when (index) {
                0 -> {
                    AuthCreateAccount(
                        navController = navController,
                        coroutineScope = coroutineScope
                    )
                }
                1 -> {
                    AuthLogin(
                        navController = navController,
                        coroutineScope = coroutineScope
                    )
                }
            }
        }
    }
}