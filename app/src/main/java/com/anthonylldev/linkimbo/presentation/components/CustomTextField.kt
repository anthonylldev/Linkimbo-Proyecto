package com.anthonylldev.linkimbo.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    onValueChange: (String) -> Unit = {},
    text: String = "",
    modifier: Modifier = Modifier,
) {

    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(percent = 20)),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}