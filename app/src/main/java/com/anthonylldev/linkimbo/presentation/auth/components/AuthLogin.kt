package com.anthonylldev.linkimbo.presentation.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AuthLogin() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Login")
    }
}