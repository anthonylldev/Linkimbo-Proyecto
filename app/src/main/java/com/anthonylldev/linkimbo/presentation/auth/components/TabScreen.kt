package com.anthonylldev.linkimbo.presentation.auth.components

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TabScreen(
    selectIndex: Int,
    onSelect: (TabPages) -> Unit
) {
    TabRow(selectedTabIndex = selectIndex) {
        TabPages.values().forEachIndexed { index, tabPage ->
            Tab(
                selected = index == selectIndex,
                onClick = { onSelect(tabPage) },
                text = { Text(text = tabPage.text) }
            )
        }
    }
}

enum class TabPages(val text: String) {
    Create("Create Acount"),
    LogIn("Log in")
}