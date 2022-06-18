package com.anthonylldev.linkimbo.profile.domain.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.profile.domain.presentation.profile.components.ProfileHeader
import com.anthonylldev.linkimbo.profile.domain.presentation.profile.components.ProfilePostSection
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    userId: String?
) {

    userId?.let { profileViewModel.setUserId(it) }
    profileViewModel.loadProfile()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        StandarToolbar(
            navController = navController,
            modifier = Modifier.fillMaxWidth(),
            title = profileViewModel.profile.value?.username ?: "",
            showBackArrow = true,
            navActions = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    tint = MaterialTheme.colors.background,
                    modifier = Modifier.padding(SpaceMedium)
                )
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                ProfileHeader(
                    navController = navController,
                    profile = profileViewModel.profile.value
                )
            }

            item {
                Spacer(modifier = Modifier.height(SpaceMedium))
            }

            profileViewModel.loadPosts(userId)

            item {
                FlowRow {
                    ProfilePostSection(
                        navController = navController,
                        posts = profileViewModel.posts.value
                    )
                }
            }

        }
    }
}