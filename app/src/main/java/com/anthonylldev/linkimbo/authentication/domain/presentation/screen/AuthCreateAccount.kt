package com.anthonylldev.linkimbo.authentication.domain.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.anthonylldev.linkimbo.authentication.domain.presentation.AuthViewModel
import com.anthonylldev.linkimbo.authentication.domain.presentation.CreateAccountViewModel
import com.anthonylldev.linkimbo.authentication.domain.presentation.screen.util.AuthPages
import com.anthonylldev.linkimbo.util.navigation.Screen
import com.anthonylldev.linkimbo.util.ui.components.CustomButton
import com.anthonylldev.linkimbo.util.ui.components.CustomTextField
import com.anthonylldev.linkimbo.util.ui.components.GoogleButton
import com.anthonylldev.linkimbo.util.ui.theme.SpaceExtraLarge
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.util.ui.theme.SpaceSmall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AuthCreateAccount(
    navController: NavController,
    coroutineScope: CoroutineScope,
    authViewModel: AuthViewModel = hiltViewModel(),
    createAccountViewModel: CreateAccountViewModel = hiltViewModel()
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
            text = createAccountViewModel.usernameText.value,
            hint = stringResource(id = R.string.username),
            error = createAccountViewModel.usernameError.value,
            onValueChange = {
                createAccountViewModel.setUsernameText(it)
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
            text = createAccountViewModel.emailText.value,
            hint = stringResource(id = R.string.email),
            error = createAccountViewModel.emailError.value,
            keyboardType = KeyboardType.Email,
            onValueChange = {
                createAccountViewModel.setEmailText(it)
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
            text = createAccountViewModel.passwordText.value,
            hint = stringResource(id = R.string.password),
            error = createAccountViewModel.passwordError.value,
            keyboardType = KeyboardType.Password,
            onValueChange = {
                createAccountViewModel.setPasswordText(it)
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    // TODO
                }
            ),
            imeAction = ImeAction.Done,
            showPasswordToggle = createAccountViewModel.passwordVisibility.value,
            onPasswordToggleClick = {
                createAccountViewModel.setPasswordVisibility(it)
            }
        )

        Spacer(modifier = Modifier.height(SpaceExtraLarge))

        CustomButton(
            text = stringResource(id = R.string.continueS),
            displayProgressBar = createAccountViewModel.isLoading()
        ) {
            createAccountViewModel.createAccount()

            if (createAccountViewModel.createAccountSuccessful.value) {
                navController.navigate(Screen.MainFeedScreen.route)
            } else {
                /* TODO( modal error message ) */
            }
        }

        Spacer(modifier = Modifier.height(SpaceMedium))

        GoogleButton {
            /* TODO( register with google ) */
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
                        authViewModel.scrollPage(AuthPages.LogIn)
                    }
                }
        )
    }
}