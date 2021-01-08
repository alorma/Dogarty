package com.alorma.dogarty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.alorma.dogarty.ui.DogartyTheme
import com.alorma.dogarty.ui.model.AppUser
import com.alorma.dogarty.ui.screens.FullScreenLoading
import com.alorma.dogarty.ui.screens.MainViewModel
import com.alorma.dogarty.ui.screens.gowalk.GoWalkScreen
import com.alorma.dogarty.ui.screens.login.LoginScreen
import com.alorma.dogarty.ui.screens.user.UserScreen
import org.koin.androidx.compose.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DogartyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable("splash") { InitScreen(navController) }
                    composable("login") { LoginScreen(navController) }
                    composable("user") { UserScreen(navController) }
                    composable("gowalk") { GoWalkScreen(navController) }
                }
            }
        }
    }
}

@Composable
fun InitScreen(
    navController: NavController,
    viewModel: MainViewModel = getViewModel(),
) {
    val state = viewModel.userState.collectAsState()
    when (state.value) {
        AppUser.NotLogged -> navController.navigate("login")
        AppUser.Logged -> navController.navigate("user")
        AppUser.Loading -> FullScreenLoading()
    }
}
