package com.anthonylldev.linkimbo.presentation.edit_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.presentation.components.CustomTextField
import com.anthonylldev.linkimbo.presentation.components.StandarToolbar
import com.anthonylldev.linkimbo.presentation.ui.theme.HintGray
import com.anthonylldev.linkimbo.presentation.ui.theme.ProfileSize
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.presentation.util.TextFieldState

@Composable
fun EditProfileScreen(
    navController: NavController,
    editProfileViewModel: EditProfileViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        StandarToolbar(
            navController = navController,
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.edit_profile),
            showBackArrow = true,
            navActions = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = HintGray,
                    modifier = Modifier.padding(SpaceMedium)
                )
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.anthony_profile_square),
                contentDescription = "user.profilePictureUrl",
                modifier = Modifier
                    .padding(SpaceMedium)
                    .size(ProfileSize)
                    .clip(MaterialTheme.shapes.large)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SpaceMedium),
            ) {
                CustomTextField(
                    text = editProfileViewModel.nameState.value.text,
                    hint = stringResource(id = R.string.name),
                    error = editProfileViewModel.nameState.value.error,
                    onValueChange = {
                        editProfileViewModel.setNameState(TextFieldState(it))
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
                    text = editProfileViewModel.username.value.text,
                    hint = stringResource(id = R.string.username),
                    error = editProfileViewModel.username.value.error,
                    onValueChange = {
                        editProfileViewModel.setUsernameState(TextFieldState(it))
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
                    text = editProfileViewModel.biography.value.text,
                    hint = stringResource(id = R.string.biography),
                    error = editProfileViewModel.biography.value.error,
                    onValueChange = {
                        editProfileViewModel.setBiographyState(TextFieldState(it))
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
                    text = editProfileViewModel.website.value.text,
                    hint = stringResource(id = R.string.website),
                    error = editProfileViewModel.website.value.error,
                    onValueChange = {
                        editProfileViewModel.setWebsiteState(TextFieldState(it))
                    },
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.clearFocus()
                        }
                    ),
                    imeAction = ImeAction.Next
                )
            }
        }
    }
}