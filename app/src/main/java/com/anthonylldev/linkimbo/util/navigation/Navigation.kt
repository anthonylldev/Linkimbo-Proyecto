package com.anthonylldev.linkimbo.util.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.presentation.activity.ActivityScreen
import com.anthonylldev.linkimbo.post.domain.presentation.create_post.CreatePostScreen
import com.anthonylldev.linkimbo.main_feed.domain.presentation.main_feed.MainFeedScreen
import com.anthonylldev.linkimbo.authentication.domain.presentation.screen.AuthScreen
import com.anthonylldev.linkimbo.chat.domain.presentation.ChatScreen
import com.anthonylldev.linkimbo.profile.domain.presentation.edit_profile.EditProfileScreen
import com.anthonylldev.linkimbo.post.domain.presentation.post_detail.PostDetailScreen
import com.anthonylldev.linkimbo.profile.domain.presentation.profile.ProfileScreen
import com.anthonylldev.linkimbo.splash.SplashScreen

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
            Screen.CreatePostScreen.route
        ) {
            CreatePostScreen(navController = navController)
        }

        composable(
            Screen.PostDetailScreen.route
        ) {
            PostDetailScreen(navController = navController)
        }

        composable(
            route = Screen.ProfileScreen.route + "?userId={userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = ""
                }
            )
        ) {
            ProfileScreen(
                navController = navController,
                userId = it.arguments?.getString("userId"),
            )
        }

        composable(
            Screen.EditProfileScreen.route
        ) {
            EditProfileScreen(navController = navController)
        }
    }
}