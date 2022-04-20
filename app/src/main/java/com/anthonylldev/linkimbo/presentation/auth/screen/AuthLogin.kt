package com.anthonylldev.linkimbo.presentation.auth.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
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

        Text(
            text = stringResource(id = R.string.username),
            modifier = Modifier
                .padding(start = 6.dp),
            style = TextStyle(
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )

        CustomTextField(
            text = viewModel.username.value,
            onValueChange = {
                viewModel.setEmailText(it)
            }
        )
        
        Spacer(modifier = Modifier.height(SpaceMedium))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.password),
                modifier = Modifier
                    .padding(start = 6.dp),
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )

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

        CustomTextField(
            text = viewModel.passwordText.value,
            keyboardType = KeyboardType.Password,
            onValueChange = {
                viewModel.setPasswordText(it)
            }
        )

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