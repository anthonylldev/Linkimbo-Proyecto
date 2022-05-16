package com.anthonylldev.linkimbo.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.presentation.components.CustomButton
import com.anthonylldev.linkimbo.presentation.components.CustomOutlinedButton
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.presentation.util.Screen

@Composable
fun ButtonProfileSection(
    navController: NavController,
    isOwnProfile: Boolean,
    isFollowing: Boolean,
    onFollowClick: () -> Unit = {},
    onMessageClick: () -> Unit = {}
) {

    if (isOwnProfile) {
        CustomButton(
            text = stringResource(id = R.string.edit_profile),
            modifier = Modifier
                .height(35.dp)
                .padding(horizontal = SpaceMedium)
        ) {
            navController.navigate(Screen.EditProfileScreen.route)
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceMedium),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            if (isFollowing) {
                CustomOutlinedButton(
                    text = stringResource(id = R.string.unfollow),
                    modifier = Modifier
                        .width(150.dp)
                        .height(35.dp),
                    onClick = onFollowClick
                )
            } else {
                CustomButton(
                    text = stringResource(id = R.string.follow),
                    modifier = Modifier
                        .width(150.dp)
                        .height(35.dp),
                    onClick = onFollowClick
                )
            }

            CustomOutlinedButton(
                text = stringResource(id = R.string.message),
                modifier = Modifier
                    .width(150.dp)
                    .height(35.dp),
                onClick = onMessageClick
            )
        }
    }
}