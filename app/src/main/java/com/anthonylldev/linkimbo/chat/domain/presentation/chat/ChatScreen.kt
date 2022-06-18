package com.anthonylldev.linkimbo.chat.domain.presentation.chat

import androidx.compose.foundation.clickable
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
import com.anthonylldev.linkimbo.chat.domain.presentation.component.Message
import com.anthonylldev.linkimbo.util.navigation.Screen
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.util.ui.presentation.UiEvent
import com.anthonylldev.linkimbo.util.ui.theme.HintGray
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        chatViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Like -> chatViewModel.messages()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        StandarToolbar(
            navController = navController,
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.your_feed),
            showBackArrow = true,
            navActions = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(id = R.string.profile_icon),
                    tint = HintGray,
                    modifier = Modifier
                        .padding(SpaceMedium)
                        .clickable {
                            navController.navigate(Screen.ProfileScreen.route + "?userId=${"me"}")
                        }
                )
            }
        )

        chatViewModel.messages()

        if (!chatViewModel.isLoading.value) {
            LazyColumn(
                reverseLayout = true
            ) {
                items(chatViewModel.allMessages.value) { message ->
                    Message(message)
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