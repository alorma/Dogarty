package com.alorma.dogarty.ui.screens.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.alorma.dogarty.domain.model.PetDetail
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
fun UserScreen(
    loggedUserViewModel: LoggedUserViewModel = getViewModel()
) {
    val userState = loggedUserViewModel.userDetailState.collectAsState()

    when (val value = userState.value.also { Timber.tag("ALORMA-DATA").v(it.toString()) }) {
        UserState.Loading -> CircularProgressIndicator()
        is UserState.UserReady -> UserReady(value)
        UserState.Error -> Text(text = "error")
    }
}

@Composable
fun UserReady(
    value: UserState.UserReady,
    loggedUserViewModel: LoggedUserViewModel = getViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                title = {
                    Column {
                        Text(text = value.nick)
                        Text(
                            style = MaterialTheme.typography.subtitle1,
                            text = value.created.toString()
                        )
                    }
                },
            )
        },
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(imageVector = Icons.Default.NearMe)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                cutoutShape = RoundedCornerShape(percent = 50),
            ) {
                IconButton(onClick = {}) { Icon(imageVector = Icons.Default.Menu) }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {}) { Icon(imageVector = Icons.Default.Add) }
            }
        }
    ) {
        val state = loggedUserViewModel.petsDetailState.collectAsState()
        when (val petsValue = state.value) {
            PetsState.Loading -> CircularProgressIndicator()
            is PetsState.PetsReady -> PetsReady(petsValue.items)
            PetsState.Error -> Text(text = "error")
        }

    }
}

@Composable
fun PetsReady(items: List<PetDetail>) {
    LazyColumn {
        items(items) { item ->
            Text(text = item.name)
        }
    }
}
