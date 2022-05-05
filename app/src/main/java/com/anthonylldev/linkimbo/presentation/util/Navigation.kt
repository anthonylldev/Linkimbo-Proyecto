package com.anthonylldev.linkimbo.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anthonylldev.linkimbo.presentation.activity.ActivityScreen
import com.anthonylldev.linkimbo.presentation.add.AddScreen
import com.anthonylldev.linkimbo.presentation.main_feed.MainFeedScreen
import com.anthonylldev.linkimbo.presentation.auth.screen.AuthScreen
import com.anthonylldev.linkimbo.presentation.chat.ChatScreen
import com.anthonylldev.linkimbo.presentation.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
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
    }
}