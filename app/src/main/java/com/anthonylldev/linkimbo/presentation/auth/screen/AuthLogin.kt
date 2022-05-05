package com.anthonylldev.linkimbo.presentation.auth.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.presentation.auth.AuthViewModel
import com.anthonylldev.linkimbo.presentation.auth.LoginViewModel
import com.anthonylldev.linkimbo.presentation.auth.screen.util.AuthPages
import com.anthonylldev.linkimbo.presentation.components.GoogleButton
import com.anthonylldev.linkimbo.presentation.components.CustomButton
import com.anthonylldev.linkimbo.presentation.components.CustomTextField
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceSmall
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AuthLogin(
    navController: NavController,
    coroutineScope: CoroutineScope,
    authViewModel: AuthViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current

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
            text = loginViewModel.username.value,
            hint = stringResource(id = R.string.username),
            error = loginViewModel.usernameError.value,
            onValueChange = {
                loginViewModel.setUsernameText(it)
            },
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(SpaceMedium))

        CustomTextField(
            text = loginViewModel.passwordText.value,
            hint = stringResource(id = R.string.password),
            error = loginViewModel.passwordError.value,
            keyboardType = KeyboardType.Password,
            onValueChange = {
                loginViewModel.setPasswordText(it)
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    // TODO
                }
            ),
            imeAction = ImeAction.Done,
            showPasswordToggle = loginViewModel.passwordVisibility.value,
            onPasswordToggleClick = {
                loginViewModel.setPasswordVisibility(it)
            }
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            ClickableText(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontSize = 16.sp,
                        )
                    ) {
                        append(stringResource(id = R.string.forgot_pass))
                    }
                }
            ) {
                // TODO
            }
        }

        Spacer(modifier = Modifier.height(SpaceMedium))

        CustomButton(
            text = stringResource(id = R.string.log_in)
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
                append(stringResource(id = R.string.have_account))
                append(" ")

                val signUpText = stringResource(id = R.string.sign_in)
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
                        authViewModel.scrollPage(AuthPages.Create)
                    }
                }
        )
    }
}