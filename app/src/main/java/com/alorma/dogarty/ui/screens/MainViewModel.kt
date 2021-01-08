package com.alorma.dogarty.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alorma.dogarty.auth.AppAuth
import com.alorma.dogarty.ui.model.AppUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val appAuth: AppAuth) : ViewModel() {

    private val _userState: MutableStateFlow<AppUser> = MutableStateFlow(AppUser.Loading)
    val userState: StateFlow<AppUser> = _userState

    init {
        viewModelScope.launch {
            val user = appAuth.auth()
            _userState.emit(user)
        }
    }
}