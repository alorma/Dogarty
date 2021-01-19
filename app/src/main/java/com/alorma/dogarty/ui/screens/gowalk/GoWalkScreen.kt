package com.alorma.dogarty.ui.screens.gowalk

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alorma.dogarty.R
import com.alorma.dogarty.ui.components.DropdownComponent
import com.alorma.dogarty.ui.components.NavigationAppTopBar
import java.time.Duration
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

@Composable
fun GoWalkScreen(navController: NavController) {

    val placeSelection = remember { mutableStateOf("") }
    val timeSelection = remember { mutableStateOf(Duration.ZERO) }

    Scaffold(
        topBar = {
            NavigationAppTopBar(onBack = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.screen_go_walk_title))
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            DropdownComponent(
                label = "Place",
                items = listOf("Parc La Cogullada", "PipiCan", "Jutjats"),
            ) { item ->
                placeSelection.value = item
            }
            Spacer(modifier = Modifier.preferredHeight(8.dp))
            DropdownComponent(
                label = "Time",
                items = listOf(Duration.ofHours(1), Duration.ofHours(1).plusMinutes(30)),
                itemFormatter = { duration ->
                    duration.toString()
                }
            ) { item ->
                timeSelection.value = item
            }
        }
    }
}