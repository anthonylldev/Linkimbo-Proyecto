package com.anthonylldev.linkimbo.presentation.auth.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.presentation.auth.LoginViewModel
import com.anthonylldev.linkimbo.presentation.components.ClickableHere
import com.anthonylldev.linkimbo.presentation.components.GoogleButton
import com.anthonylldev.linkimbo.presentation.components.CustomButton
import com.anthonylldev.linkimbo.presentation.components.CustomTextField
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceSmall

@Composable
fun AuthLogin(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
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
            text = viewModel.username.value,
            hint = stringResource(id = R.string.username),
            error = viewModel.usernameError.value,
            onValueChange = {
                viewModel.setUsernameText(it)
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

        ClickableHere(text = stringResource(id = R.string.no_account))
    }
}