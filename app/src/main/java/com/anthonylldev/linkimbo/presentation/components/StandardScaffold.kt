package com.anthonylldev.linkimbo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.domain.models.BottomNavItem
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
        BottomNavItem(route = ""),
        BottomNavItem(
            route = Screen.ActivityScreen.route,
            icon = Icons.Default.Favorite,
            contentDescription = "Favourite"
        ),
        BottomNavItem(
            route = Screen.ChatScreen.route,
            icon = Icons.Default.Send,
            contentDescription = "Send"
        )
    ),
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    val gradient = Brush.verticalGradient(
        listOf(
            MaterialTheme.colors.secondary,
            MaterialTheme.colors.primary
        )
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomAppBar(
                    modifier = Modifier,
                    backgroundColor = Color.Black,
                    cutoutShape = CircleShape,
                    elevation = 5.dp
                ) {
                    BottomNavigation(
                        backgroundColor = Color.Black
                    ) {
                        bottomNavItems.forEachIndexed { i, bottomNavItem ->
                            StandardBottomNavItem(
                                icon = bottomNavItem.icon,
                                contentDescription = bottomNavItem.contentDescription,
                                selected = bottomNavItem.route == navController.currentDestination?.route,
                                alertCount = bottomNavItem.alertCount,
                                enabled = bottomNavItem.icon != null
                            ) {
                                if (navController.currentDestination?.route != bottomNavItem.route) {
                                    navController.navigate(bottomNavItem.route)
                                }
                            }
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            if (showBottomBar) {
                IconButton(
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .clip(CircleShape)
                        .background(gradient),
                    onClick = { onFabClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(id = R.string.link_fab),
                        tint = Color.White
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
    ) {
        content()
    }
}