package com.alorma.dogarty.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun AppTopBar(title: @Composable () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primarySurface,
        title = title,
    )
}

@Composable
fun NavigationAppTopBar(
    onBack: () -> Unit,
    title: @Composable () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primarySurface,
        title = title,
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack)
            }
        }
    )
}