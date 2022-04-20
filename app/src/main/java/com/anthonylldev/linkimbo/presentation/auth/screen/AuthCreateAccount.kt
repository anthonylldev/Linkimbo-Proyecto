package com.anthonylldev.linkimbo.presentation.auth.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.presentation.auth.CreateAccountViewModel
import com.anthonylldev.linkimbo.presentation.auth.screen.util.AuthPages
import com.anthonylldev.linkimbo.presentation.components.CustomButton
import com.anthonylldev.linkimbo.presentation.components.CustomTextField
import com.anthonylldev.linkimbo.presentation.components.GoogleButton
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceExtraLarge
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceSmall
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AuthCreateAccount(
    navController: NavController,
    pagerSelect: PagerState,
    coroutineScope: CoroutineScope,
    viewModel: CreateAccountViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 50.dp,
                end = 50.dp,
                bottom = 32.dp
            ),
        verticalArrangement = Arrangement.Bottom
    ) {

        CustomTextField(
            text = viewModel.usernameText.value,
            hint = stringResource(id = R.string.username),
            error = viewModel.usernameError.value,
            onValueChange = {
                viewModel.setUsernameText(it)
            }
        )

        Spacer(modifier = Modifier.height(SpaceMedium))

        CustomTextField(
            text = viewModel.emailText.value,
            hint = stringResource(id = R.string.email),
            error = viewModel.emailError.value,
            keyboardType = KeyboardType.Email,
            onValueChange = {
                viewModel.setEmailText(it)
            }
        )

        Spacer(modifier = Modifier.height(SpaceMedium))

        CustomTextField(
            text = viewModel.passwordText.value,
            hint = stringResource(id = R.string.password),
            error = viewModel.passwordError.value,
            keyboardType = KeyboardType.Password,
            onValueChange = {
                viewModel.setPasswordText(it)
            },
            showPasswordToggle = viewModel.passwordVisibility.value,
            onPasswordToggleClick = {
                viewModel.setPasswordVisibility(it)
            }
        )
        
        Spacer(modifier = Modifier.height(SpaceExtraLarge))

        CustomButton(
            text = stringResource(id = R.string.continueS)
        ) {
            // TODO
        }

        Spacer(modifier = Modifier.height(SpaceMedium))

        GoogleButton {
            // TODO
        }

        Spacer(modifier = Modifier.height(SpaceSmall))
        
        Text(
            text = buildAnnotatedString { 
                append(stringResource(id = R.string.no_account))
                append(" ")
                
                val signUpText = stringResource(id = R.string.sign_up)
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary
                    )
                ) {
                    append(signUpText)
                }
            },
            style = TextStyle(fontSize = 14.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        pagerSelect.animateScrollToPage(page = AuthPages.LogIn.ordinal)
                    }
                }
        )
    }
}