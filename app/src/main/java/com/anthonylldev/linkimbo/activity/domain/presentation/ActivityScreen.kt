package com.anthonylldev.linkimbo.activity.domain.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.activity.domain.presentation.components.ActivityItem
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium
import com.anthonylldev.linkimbo.util.ui.theme.SpaceSmall
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ActivityScreen(
    navController: NavController,
    activityViewModel: ActivityViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        activityViewModel.eventFlow.collectLatest { event ->
            /*when (event) {
                is UiEvent.Like -> {
                    postDetailViewModel.loadPost(postDetailViewModel.post.value!!.id!!)
                }
            }*/
        }
    }

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

        activityViewModel.loadActivities()

        if (!activityViewModel.isLoading.value) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = SpaceMedium)
            ) {
                items(activityViewModel.allActivities.value) { activity ->
                    ActivityItem(activity)

                    if (activityViewModel.allActivities.value.size < 19) {
                        Spacer(modifier = Modifier.height(SpaceSmall))
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    strokeWidth = 6.dp,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }
    }
}