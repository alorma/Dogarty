package com.alorma.dogarty.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.alorma.dogarty.R
import com.alorma.dogarty.ui.Navigation
import com.alorma.dogarty.ui.components.AppTopBar
import com.alorma.dogarty.ui.components.FullScreenLoadingScaffold
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = getViewModel()
) {
    val state = loginViewModel.userDetailState.collectAsState()

    when (state.value) {
        LoginState.Loading -> FullScreenLoadingScaffold()
        LoginState.NotLogged -> LoginContent(loginViewModel)
        LoginState.Logged -> {
            navController.navigate(Navigation.ROUTE_USER)
        }
        LoginState.Fail -> Text(text = "Login error")
    }
}

@Composable
fun LoginContent(loginViewModel: LoginViewModel) {
    Scaffold(
        topBar = {
            AppTopBar {
                Text(text = stringResource(id = R.string.app_name))
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                loginViewModel.login()
            }) {
                Text(text = "Login")
            }
        }
    }
}
