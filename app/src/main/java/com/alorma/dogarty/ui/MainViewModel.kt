package com.alorma.dogarty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alorma.dogarty.auth.AppAuth
import com.alorma.dogarty.ui.model.AppUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val appAuth: AppAuth,
) : ViewModel() {

    private val _userDetailState: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val userDetailState: StateFlow<Boolean?> = _userDetailState

    init {
        viewModelScope.launch {
            when (appAuth.auth()) {
                AppUser.NotLogged -> _userDetailState.emit(false)
                AppUser.Logged -> _userDetailState.emit(true)
            }
        }
    }
}