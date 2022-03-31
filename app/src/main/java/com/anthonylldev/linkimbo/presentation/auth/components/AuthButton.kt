package com.anthonylldev.linkimbo.presentation.util.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    text: String,
    displayProgressBar: Boolean = false,
    buttonBordered: Boolean = false,
    onClick: () -> Unit
) {
    val gradient = Brush.horizontalGradient(listOf(
        MaterialTheme.colors.surface,
        MaterialTheme.colors.primary
    ))

    if (!displayProgressBar) {
        if (!buttonBordered) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                shape = RoundedCornerShape(20),
                contentPadding = PaddingValues(),
                onClick = onClick,
                modifier = modifier
                    .height(50.dp),
            ) {
                Box(
                    modifier = Modifier
                        .background(gradient)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = text,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
        } else {
            OutlinedButton(
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                shape = RoundedCornerShape(20),
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colors.primary,
                ),
                modifier = modifier
                    .height(50.dp),
                onClick = onClick,
            ) {
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground
                    )
                )
            }
        }
    } else {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
            strokeWidth = 6.dp,
            modifier = Modifier
                .size(50.dp)
        )
    }
}