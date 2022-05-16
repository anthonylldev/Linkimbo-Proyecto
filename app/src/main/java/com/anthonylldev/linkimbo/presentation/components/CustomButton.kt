package com.anthonylldev.linkimbo.presentation.components

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
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    displayProgressBar: Boolean = false,
    onClick: () -> Unit
) {
    val gradient = Brush.horizontalGradient(listOf(
        MaterialTheme.colors.secondary,
        MaterialTheme.colors.primary
    ))

    if (!displayProgressBar) {
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
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
            strokeWidth = 6.dp,
            modifier = Modifier
                .size(50.dp)
        )
    }
}