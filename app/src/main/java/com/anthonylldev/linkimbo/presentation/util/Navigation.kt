package com.anthonylldev.linkimbo.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anthonylldev.linkimbo.domain.models.User
import com.anthonylldev.linkimbo.presentation.activity.ActivityScreen
import com.anthonylldev.linkimbo.presentation.add.AddScreen
import com.anthonylldev.linkimbo.presentation.main_feed.MainFeedScreen
import com.anthonylldev.linkimbo.presentation.auth.screen.AuthScreen
import com.anthonylldev.linkimbo.presentation.chat.ChatScreen
import com.anthonylldev.linkimbo.presentation.post_detail.PostDetailScreen
import com.anthonylldev.linkimbo.presentation.profile.ProfileScreen
import com.anthonylldev.linkimbo.presentation.splash.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(
            Screen.SplashScreen.route
        ) {
            SplashScreen(navController = navController)
        }

        composable(
            Screen.AuthScreen.route
        ) {
            AuthScreen(navController = navController)
        }

        composable(
            Screen.MainFeedScreen.route
        ) {
            MainFeedScreen(navController = navController)
        }

        composable(
            Screen.ChatScreen.route
        ) {
            ChatScreen(navController = navController)
        }

        composable(
            Screen.ActivityScreen.route
        ) {
            ActivityScreen(navController = navController)
        }

        composable(
            Screen.AddScreen.route
        ) {
            AddScreen(navController = navController)
        }

        composable(
            Screen.PostDetailScreen.route
        ) {
            PostDetailScreen(navController = navController)
        }

        composable(
            Screen.ProfileScreen.route
        ) {
            ProfileScreen(
                navController = navController,
                user = User(
                    profilePictureUrl = "",
                    username = "anthonylldev",
                    realname = "Anthony",
                    description = "I am a full stack and mobile developer, I am a full stack and mobile developer, I am a full stack and mobile developer.",
                    website = "www.anthonylldev.com",
                    followerCount = 69,
                    followingCount = 96,
                    postCount = 2
                )
            )
        }
    }
}