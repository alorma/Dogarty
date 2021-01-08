package com.alorma.dogarty.ui.screens.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) { item -> PetCard(item) }
    }
}

@Composable
private fun PetCard(item: PetDetail) {
    Card(
        modifier = Modifier
            .padding(top = 4.dp, start = 8.dp, end = 8.dp, bottom = 4.dp)
            .fillMaxWidth()
            .preferredHeight(100.dp)
            .clickable(
                onClick = { },
                indication = rememberRipple(color = MaterialTheme.colors.secondary)
            ),
        elevation = 4.dp
    ) {
        PetCardContent(item)
    }
}

@Composable
private fun PetCardContent(item: PetDetail) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = item.name)
    }
}
