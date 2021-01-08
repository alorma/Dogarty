package com.alorma.dogarty.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alorma.dogarty.auth.AppAuth
import com.alorma.dogarty.ui.model.AppUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val appAuth: AppAuth) : ViewModel() {

    private val _userDetailState: MutableStateFlow<AppUser> = MutableStateFlow(AppUser.Loading)
    val userDetailState: StateFlow<AppUser> = _userDetailState

    init {
        viewModelScope.launch {
            val user = appAuth.auth()
            _userDetailState.emit(user)
        }
    }

    fun login() {
        viewModelScope.launch {
            _userDetailState.emit(AppUser.Loading)
            val user = appAuth.login()
            _userDetailState.emit(user)
        }
    }
}