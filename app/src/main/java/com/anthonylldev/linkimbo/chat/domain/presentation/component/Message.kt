package com.anthonylldev.linkimbo.chat.domain.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anthonylldev.linkimbo.chat.domain.model.Message
import com.anthonylldev.linkimbo.util.Constants
import com.anthonylldev.linkimbo.util.DateFormatUtil
import com.anthonylldev.linkimbo.util.ui.theme.SpaceMedium

@Composable
fun Message(
    message: Message,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(SpaceMedium),
        backgroundColor = (MaterialTheme.colors.background),
        elevation = 5.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = DateFormatUtil.timestampToFormattedString(
                timestamp = message.timestamp,
                patter = Constants.PATTER_TIME
            )
        )

        Spacer(modifier = Modifier.height(SpaceMedium))

        Text(text = message.message)
    }
}