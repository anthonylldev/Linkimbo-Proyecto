package com.anthonylldev.linkimbo.presentation.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.domain.models.BottomNavItem
import com.anthonylldev.linkimbo.presentation.ui.theme.BottomAppBarColor
import com.anthonylldev.linkimbo.presentation.util.Screen

@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.MainFeedScreen.route,
            icon = Icons.Default.Home,
            contentDescription = "Home"
        ),
        BottomNavItem(
            route = Screen.AddScreen.route,
            icon = Icons.Default.Add,
            contentDescription = "Add"
        ),
        BottomNavItem(
            route = Screen.ActivityScreen.route,
            icon = Icons.Default.Favorite,
            contentDescription = "Favourite"
        ),
        BottomNavItem(
            route = Screen.ChatScreen.route,
            icon = Icons.Default.Message,
            contentDescription = "Message"
        )
    ),
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            if(showBottomBar) {
                BottomAppBar(
                    modifier = Modifier,
                    backgroundColor = BottomAppBarColor,
                    cutoutShape = CircleShape,
                    elevation = 5.dp
                ) {
                    BottomNavigation(
                        backgroundColor = BottomAppBarColor
                    ) {
                        bottomNavItems.forEachIndexed { i, bottomNavItem ->
                            StandardBottomNavItem(
                                icon = bottomNavItem.icon,
                                contentDescription = bottomNavItem.contentDescription,
                                selected = bottomNavItem.route == navController.currentDestination?.route,
                                alertCount = bottomNavItem.alertCount,
                            ) {
                                navController.navigate(bottomNavItem.route)
                            }
                        }
                    }
                }
            }
        },
        modifier = modifier
    ) {
        content()
    }
}