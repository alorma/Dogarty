package com.alorma.dogarty.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alorma.dogarty.auth.AppAuth
import kotlinx.coroutines.launch

class LoginViewModel(private val appAuth: AppAuth) : ViewModel() {

    fun login() {
        viewModelScope.launch {
            appAuth.login()
        }
    }
}