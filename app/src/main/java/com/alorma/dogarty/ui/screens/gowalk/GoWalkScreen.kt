package com.alorma.dogarty.ui.screens.gowalk

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.alorma.dogarty.R
import com.alorma.dogarty.ui.components.NavigationAppTopBar

@Composable
fun GoWalkScreen(navController: NavController) {
    Scaffold(
        topBar = {
            NavigationAppTopBar(onBack = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.screen_go_walk_title))
            }
        },
    ) {

    }
}