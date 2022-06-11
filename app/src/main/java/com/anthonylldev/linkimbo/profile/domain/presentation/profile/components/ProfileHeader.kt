package com.anthonylldev.linkimbo.profile.domain.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.profile.domain.model.ProfileResponse
import com.anthonylldev.linkimbo.util.ui.theme.HintGray
import com.anthonylldev.linkimbo.util.ui.theme.ProfileSize
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium

@Composable
fun ProfileHeader(
    navController: NavController,
    profile: ProfileResponse?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = profile?.followerCount.toString(),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.followers),
                style = MaterialTheme.typography.body2,
                color = HintGray
            )
        }

        Image(
            painter = painterResource(id = R.drawable.anthony_profile_square),
            contentDescription = "user.profilePictureUrl",
            modifier = Modifier
                .padding(SpaceMedium)
                .size(ProfileSize)
                .clip(MaterialTheme.shapes.large)
        )

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = profile?.followingCount.toString(),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.following),
                style = MaterialTheme.typography.body2,
                color = HintGray
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SpaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = profile?.realName.toString(),
            style = MaterialTheme.typography.h6
        )

        Text(
            text = profile?.description.toString(),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )

        Text(
            text = profile?.website.toString(),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary
        )

    }

    Spacer(modifier = Modifier.height(SpaceMedium))

    ButtonProfileSection(
        navController = navController,
        isOwnProfile = profile?.isOwnProfile ?: false,
        isFollowing =  profile?.isFollowing ?: false
    )
}