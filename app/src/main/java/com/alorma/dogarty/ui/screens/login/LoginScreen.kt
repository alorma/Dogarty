package com.alorma.dogarty.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.alorma.dogarty.ui.model.AppUser
import com.alorma.dogarty.ui.screens.FullScreenLoading
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = getViewModel()
) {

    val state = loginViewModel.userDetailState.collectAsState()

    when (state.value) {
        AppUser.Loading -> FullScreenLoading()
        AppUser.NotLogged -> LoginContent(loginViewModel)
        AppUser.Logged -> navController.navigate("user")
    }
}

@Composable
fun LoginContent(loginViewModel: LoginViewModel) {
    Scaffold {
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
