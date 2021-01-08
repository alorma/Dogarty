package com.alorma.dogarty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alorma.dogarty.ui.DogartyTheme
import com.alorma.dogarty.ui.Navigation
import com.alorma.dogarty.ui.screens.gowalk.GoWalkScreen
import com.alorma.dogarty.ui.screens.login.LoginScreen
import com.alorma.dogarty.ui.screens.user.UserScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DogartyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Navigation.ROUTE_USER
                ) {
                    composable(Navigation.ROUTE_LOGIN) { LoginScreen(navController) }
                    composable(Navigation.ROUTE_USER) { UserScreen(navController) }
                    composable(Navigation.ROUTE_GO_WALK) { GoWalkScreen(navController) }
                }
            }
        }
    }
}