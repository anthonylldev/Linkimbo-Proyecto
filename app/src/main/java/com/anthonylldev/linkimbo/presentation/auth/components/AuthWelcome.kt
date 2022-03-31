package com.anthonylldev.linkimbo.presentation.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.anthonylldev.linkimbo.R

@Composable
fun AuthWelcome(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                color = MaterialTheme.colors.primary,
                fontSize = 56.sp
            )
        )

        Text(
            text = stringResource(id = R.string.welcome_message),
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 16.sp
            )
        )

        Text(
            text = stringResource(id = R.string.welcome_message_2),
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }
}