package com.anthonylldev.linkimbo.util.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object AuthScreen : Screen("auth_screen")
    object MainFeedScreen : Screen("main_feed_screen")
    object ActivityScreen : Screen("activity_screen")
    object ChatScreen : Screen("chat_screen")
    object PostDetailScreen : Screen("post_detail_screen")
    object ProfileScreen : Screen("profile_screen")
    object EditProfileScreen : Screen("edit_profile_screen")
    object CreatePostScreen : Screen("create_post_screen")
    object CommentPostScreen: Screen("comment_post_screen")
}
