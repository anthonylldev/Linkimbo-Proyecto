package com.anthonylldev.linkimbo.presentation.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.activity.domain.model.Activity
import com.anthonylldev.linkimbo.util.ui.presentation.ActivityAction
import com.anthonylldev.linkimbo.util.DateFormatUtil
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.util.ui.theme.SpaceSmall
import kotlin.random.Random

@Composable
fun ActivityScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StandarToolbar(
            navController = navController,
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.activity),
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
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = SpaceMedium)
        ) {
            items(20) {
                ActivityItem(
                    activity = Activity(
                        username = "Anthony Leon Lucero",
                        actionType = if (Random.nextInt(2) == 0) {
                            ActivityAction.LikedPost
                        } else {
                            ActivityAction.CommentOnPost
                        },
                        formattedTime = DateFormatUtil.timestampToFormattedString(
                            timestamp = System.currentTimeMillis(),
                            patter = "MMM dd HH:mm"
                        )
                    )
                )
                if (it < 19) {
                    Spacer(modifier = Modifier.height(SpaceSmall))
                }
            }
        }
    }
}