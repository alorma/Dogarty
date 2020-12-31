package com.alorma.dogarty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.setContent
import com.alorma.dogarty.ui.DogartyTheme
import com.alorma.dogarty.ui.model.AppUser
import com.alorma.dogarty.ui.screens.MainViewModel
import com.alorma.dogarty.ui.screens.login.LoginScreen
import com.alorma.dogarty.ui.screens.user.UserScreen
import org.koin.androidx.compose.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DogartyTheme {
                InitScreen()
            }
        }
    }
}

@Composable
fun InitScreen(viewModel: MainViewModel = getViewModel()) {

    val state = viewModel.userState.collectAsState()

    when (state.value) {
        AppUser.NotLogged -> LoginScreen()
        AppUser.Logged -> UserScreen()
    }

}
