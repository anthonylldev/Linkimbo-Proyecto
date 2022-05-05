package com.anthonylldev.linkimbo.presentation.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object AuthScreen : Screen("auth_screen")
    object MainFeedScreen : Screen("main_feed_screen")
    object PostDetailScreen : Screen("post_detail_screen")
    object ChatScreen : Screen("char_screen")
    object ProfileScreen : Screen("profile_screen")
    object EditProfileScreen : Screen("edit_profile_screen")
    object PersonListScreen : Screen("person_list_screen")
    object CreatePostScreen : Screen("create_post_screen")
    object SearchScreen : Screen("splash_screen")
    object ActivityScreen : Screen("activity_screen")
    object AddScreen: Screen("add_screen")
}
