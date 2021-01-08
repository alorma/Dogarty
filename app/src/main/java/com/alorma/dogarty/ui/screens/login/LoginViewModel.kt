package com.alorma.dogarty.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alorma.dogarty.auth.AppAuth
import com.alorma.dogarty.ui.model.AppUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val appAuth: AppAuth) : ViewModel() {

    private val _userDetailState: MutableStateFlow<LoginState> =
        MutableStateFlow(LoginState.NotLogged)
    val userDetailState: StateFlow<LoginState> = _userDetailState

    init {
        viewModelScope.launch {
            _userDetailState.emit(LoginState.NotLogged)

            val auth = appAuth.auth()
            onAuthState(auth)
        }
    }

    private suspend fun onAuthState(auth: AppUser) = when (auth) {
        AppUser.NotLogged -> _userDetailState.emit(LoginState.NotLogged)
        AppUser.Logged -> _userDetailState.emit(LoginState.Logged)
    }

    fun login() {
        viewModelScope.launch {
            _userDetailState.emit(LoginState.Loading)
            val user = appAuth.login()
            onAuthState(user)
        }
    }
}