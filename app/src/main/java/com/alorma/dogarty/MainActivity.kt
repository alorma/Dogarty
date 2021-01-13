package com.alorma.dogarty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alorma.developer_shortcuts.ShortcutsModule
import com.alorma.dogarty.ui.DogartyTheme
import com.alorma.dogarty.ui.MainViewModel
import com.alorma.dogarty.ui.Navigation
import com.alorma.dogarty.ui.screens.gowalk.GoWalkScreen
import com.alorma.dogarty.ui.screens.login.LoginScreen
import com.alorma.dogarty.ui.screens.user.UserScreen
import com.alorma.drawer_base.DebugDrawerLayout
import com.alorma.drawer_modules.BuildModule
import com.alorma.drawer_modules.DeviceModule
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DogartyTheme {
                val loggedState = mainViewModel.userDetailState.collectAsState()
                val loggedValue = loggedState.value
                if (loggedValue != null) {
                    DebugDrawerLayout(
                        isDebug = { BuildConfig.DEBUG },
                        drawerModules = {
                            listOf(
                                ShortcutsModule(),
                                DeviceModule(),
                                BuildModule(),
                            )
                        }
                    ) {
                        CreateGraph(loggedValue)
                    }
                }
            }
        }
    }

}

@Composable
fun CreateGraph(loggedValue: Boolean) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (loggedValue) {
            Navigation.ROUTE_USER
        } else {
            Navigation.ROUTE_LOGIN
        }
    ) {
        composable(Navigation.ROUTE_LOGIN) { LoginScreen(navController) }
        composable(Navigation.ROUTE_USER) { UserScreen(navController) }
        composable(Navigation.ROUTE_GO_WALK) { GoWalkScreen(navController) }
    }
}
