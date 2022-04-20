package com.anthonylldev.linkimbo.presentation.auth.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.presentation.auth.CreateAccountViewModel
import com.anthonylldev.linkimbo.presentation.components.ClickableHere
import com.anthonylldev.linkimbo.presentation.components.CustomButton
import com.anthonylldev.linkimbo.presentation.components.CustomTextField
import com.anthonylldev.linkimbo.presentation.components.GoogleButton
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceSmall

@Composable
fun AuthCreateAccount(
    navController: NavController,
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
            text = viewModel.usernameText.value,
            onValueChange = {
                viewModel.setUsernameText(it)
            }
        )

        Spacer(modifier = Modifier.height(SpaceMedium))

        Text(
            text = stringResource(id = R.string.email),
            modifier = Modifier
                .padding(start = 6.dp),
            style = TextStyle(
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )

        CustomTextField(
            text = viewModel.emailText.value,
            keyboardType = KeyboardType.Email,
            onValueChange = {
                viewModel.setEmailText(it)
            }
        )

        Spacer(modifier = Modifier.height(SpaceMedium))

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

        CustomTextField(
            text = viewModel.passwordText.value,
            keyboardType = KeyboardType.Password,
            onValueChange = {
                viewModel.setPasswordText(it)
            },
            showPasswordToggle = viewModel.passwordVisibility.value,
            onPasswordToggleClick = {
                viewModel.setPasswordVisibility(it)
            }
        )
        
        Spacer(modifier = Modifier.height(SpaceMedium))

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
        
        ClickableHere(text = stringResource(id = R.string.have_account))
    }
}