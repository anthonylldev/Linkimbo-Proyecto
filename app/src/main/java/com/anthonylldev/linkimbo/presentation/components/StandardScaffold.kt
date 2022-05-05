package com.anthonylldev.linkimbo.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.presentation.ui.theme.BottomAppBarColor

@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = BottomAppBarColor,
                cutoutShape = CircleShape,
                elevation = 5.dp
            ) {
                BottomNavigation() {
                    StandardBottomNavItem(
                        icon = Icons.Default.Home,
                        contentDescription = stringResource(id = R.string.home),
                        selected = true
                    ) {

                    }

                    StandardBottomNavItem(
                        icon = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.chat),
                        selected = false,
                    ) {

                    }

                    StandardBottomNavItem(
                        icon = Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.chat),
                        selected = false,
                        alertCount = 50
                    ) {

                    }

                    StandardBottomNavItem(
                        icon = Icons.Default.Send,
                        contentDescription = stringResource(id = R.string.chat),
                        selected = false,
                        alertCount = 50
                    ) {

                    }
                }
            }
        },
        modifier = modifier
    ) {
        content()
    }
}