package com.alorma.dogarty.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun AppTopBar(title: @Composable () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        title = title,
    )
}