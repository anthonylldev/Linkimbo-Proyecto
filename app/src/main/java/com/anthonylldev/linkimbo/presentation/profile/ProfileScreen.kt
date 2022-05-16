package com.anthonylldev.linkimbo.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.domain.models.User
import com.anthonylldev.linkimbo.presentation.components.StandarToolbar
import com.anthonylldev.linkimbo.presentation.profile.components.ProfileHeader
import com.anthonylldev.linkimbo.presentation.profile.components.ProfilePostSection
import com.anthonylldev.linkimbo.presentation.ui.theme.SpaceMedium
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    user: User,
    isOwnProfile: Boolean = false,
    isFollowing:  Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        StandarToolbar(
            navController = navController,
            modifier = Modifier.fillMaxWidth(),
            title = user.username,
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
                    user = user,
                    isOwnProfile = isOwnProfile,
                    isFollowing = isFollowing
                )
            }
            
            item { 
                Spacer(modifier = Modifier.height(SpaceMedium))
            }

            item {
                FlowRow {
                    ProfilePostSection(
                        navController = navController
                    )
                }
            }
        }
    }
}