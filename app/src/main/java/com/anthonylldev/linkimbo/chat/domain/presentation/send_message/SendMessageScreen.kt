package com.anthonylldev.linkimbo.chat.domain.presentation.send_message

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anthonylldev.linkimbo.R
import com.anthonylldev.linkimbo.util.ui.components.CustomTextField
import com.anthonylldev.linkimbo.util.ui.components.StandarToolbar
import com.anthonylldev.linkimbo.util.ui.presentation.TextFieldState
import com.anthonylldev.linkimbo.util.ui.theme.HintGray
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium

@Composable
fun SendMessageScreen(
    navController: NavController,
    sendMessageViewModel: SendMessageViewModel = hiltViewModel(),
    userId: String?,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpaceMedium)
    ) {
        StandarToolbar(
            navController = navController,
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.create_post),
            showBackArrow = true,
            navActions = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "",
                    tint = HintGray,
                    modifier = Modifier
                        .padding(SpaceMedium)
                        .clickable {
                            sendMessageViewModel.sendMessage(userId)
                        }
                )
            }
        )

        CustomTextField(
            text = sendMessageViewModel.message.value.text,
            error = sendMessageViewModel.message.value.error,
            onValueChange = {
                sendMessageViewModel.setMessageState(TextFieldState(it))
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    sendMessageViewModel.sendMessage(userId)
                }
            ),
            imeAction = ImeAction.Done,
            hint = "Message"
        )
    }
}