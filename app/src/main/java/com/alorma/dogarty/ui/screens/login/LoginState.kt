package com.alorma.dogarty.ui.screens.login

sealed class LoginState {
    object Loading: LoginState()
    object Logged: LoginState()
    object NotLogged: LoginState()
    object Fail: LoginState()
}