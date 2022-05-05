package com.anthonylldev.linkimbo.presentation.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.domain.models.BottomNavItem
import com.anthonylldev.linkimbo.presentation.ui.theme.BottomAppBarColor

@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: StandardScaffoldViewModel = hiltViewModel(),
    bottomNavItems: List<BottomNavItem> = listOf(),
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
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
                            selected = viewModel.selectedBottomNavItem.value == i,
                            alertCount = bottomNavItem.alertCount,
                        ) {
                            viewModel.setSelectedBottomNavItem(i)
                            navController.navigate(bottomNavItem.route)
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