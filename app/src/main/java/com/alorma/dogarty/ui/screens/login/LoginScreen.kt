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
import com.alorma.dogarty.R
import com.alorma.dogarty.ui.NavigationResult
import com.alorma.dogarty.ui.screens.FullScreenLoadingScaffold
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = getViewModel()
) {
    val state = loginViewModel.userDetailState.collectAsState()

    navController.previousBackStackEntry?.savedStateHandle?.set(
        NavigationResult.LOGIN_SUCCESS,
        false
    )

    when (state.value) {
        LoginState.Loading -> FullScreenLoadingScaffold()
        LoginState.NotLogged -> LoginContent(loginViewModel)
        LoginState.Logged -> {
            navController.previousBackStackEntry?.savedStateHandle?.set(
                NavigationResult.LOGIN_SUCCESS,
                true
            )
            navController.popBackStack()
        }
        LoginState.Fail -> Text(text = "Login error")
    }
}

@Composable
fun LoginContent(loginViewModel: LoginViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
            )
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
