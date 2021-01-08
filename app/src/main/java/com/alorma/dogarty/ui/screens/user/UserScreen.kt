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
import androidx.compose.runtime.onActive
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.alorma.dogarty.domain.model.PetDetail
import com.alorma.dogarty.ui.Navigation
import com.alorma.dogarty.ui.components.AppTopBar
import com.alorma.dogarty.ui.components.FullScreenLoading
import com.alorma.dogarty.ui.components.FullScreenLoadingScaffold
import com.alorma.dogarty.utils.navToLogin
import org.koin.androidx.compose.getViewModel

@Composable
fun UserScreen(
    navController: NavController,
    loggedUserViewModel: LoggedUserViewModel = getViewModel()
) {
    val userState = loggedUserViewModel.userDetailState.collectAsState()

    when (val value = userState.value) {
        UserState.Loading -> FullScreenLoadingScaffold()
        UserState.NotLogged -> navController.navToLogin()
        is UserState.UserReady -> UserReady(value, navController)
        UserState.Error -> Text(text = "error")
    }
}

@Composable
fun UserReady(
    value: UserState.UserReady,
    navController: NavController,
    loggedUserViewModel: LoggedUserViewModel = getViewModel()
) {
    Scaffold(
        topBar = {
            AppTopBar {
                Column {
                    Text(text = value.nick)
                    Text(
                        style = MaterialTheme.typography.subtitle1,
                        text = value.created.toString()
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Navigation.ROUTE_GO_WALK) }) {
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
        onActive { loggedUserViewModel.loadPets() }
        when (val petsValue = state.value) {
            PetsState.Loading -> FullScreenLoading()
            is PetsState.PetsReady -> PetsReady(petsValue.items)
            PetsState.Empty -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No pets")
                }
            }
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
