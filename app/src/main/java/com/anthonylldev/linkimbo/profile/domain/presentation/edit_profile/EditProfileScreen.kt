package com.anthonylldev.linkimbo.profile.domain.presentation.edit_profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.util.ImageUtil
import com.anthonylldev.linkimbo.util.navigation.Screen
import com.anthonylldev.linkimbo.util.ui.components.CustomButton
import com.anthonylldev.linkimbo.util.ui.components.CustomTextField
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.util.ui.theme.HintGray
import com.anthonylldev.linkimbo.util.ui.theme.ProfileSize
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.util.ui.presentation.TextFieldState
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions

@Composable
fun EditProfileScreen(
    navController: NavController,
    editProfileViewModel: EditProfileViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current

    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // use the cropped image
            editProfileViewModel.setImageUriState(result.uriContent)
        } else {
            // an error occurred cropping
            val exception = result.error
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        val cropOptions = CropImageContractOptions(uri, CropImageOptions())
        cropOptions.setMinCropResultSize(500, 500)
        cropOptions.setMaxCropResultSize(3996, 2160)
        cropOptions.setRequestedSize(4320, 2264)
        cropOptions.setAspectRatio(1,1)
        cropOptions.setFixAspectRatio(true)
        imageCropLauncher.launch(cropOptions)
    }

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
                    modifier = Modifier
                        .padding(SpaceMedium)
                        .clickable {
                            editProfileViewModel.updateUser()
                        }
                )
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            if (editProfileViewModel.bitmap.value == null) {
                Image(
                    painter = painterResource(id = R.drawable.default_profile),
                    contentDescription = "Default picture",
                    modifier = Modifier
                        .padding(SpaceMedium)
                        .size(ProfileSize)
                        .clip(MaterialTheme.shapes.large)
                        .clickable {
                            imagePickerLauncher.launch("image/*")
                        }
                )
            } else {
                Image(
                    bitmap = ImageUtil.base64ToBitmap(editProfileViewModel.user.value!!.imageBase64!!)!!.asImageBitmap(),
                    contentDescription = "user.profilePictureUrl",
                    modifier = Modifier
                        .padding(SpaceMedium)
                        .size(ProfileSize)
                        .clip(MaterialTheme.shapes.large)
                        .clickable {
                            imagePickerLauncher.launch("image/*")
                        }
                )
            }

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
                    text = editProfileViewModel.description.value.text,
                    hint = stringResource(id = R.string.biography),
                    error = editProfileViewModel.description.value.error,
                    onValueChange = {
                        editProfileViewModel.setDescriptionState(TextFieldState(it))
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

                Spacer(modifier = Modifier.height(SpaceMedium))

                CustomButton(text = stringResource(id = R.string.close_session)) {
                    if(editProfileViewModel.closeSession()) navController.navigate(Screen.SplashScreen.route)
                }
            }
        }
    }
}