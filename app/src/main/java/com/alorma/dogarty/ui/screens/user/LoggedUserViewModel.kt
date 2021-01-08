package com.alorma.dogarty.ui.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alorma.dogarty.auth.AppAuth
import com.alorma.dogarty.domain.model.State
import com.alorma.dogarty.store.AppStore
import com.alorma.dogarty.ui.model.AppUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoggedUserViewModel(
    private val appAuth: AppAuth,
    private val appStore: AppStore
) : ViewModel() {

    private val _userDetailState: MutableStateFlow<UserState> = MutableStateFlow(UserState.Loading)
    val userDetailState: StateFlow<UserState> = _userDetailState

    private val _petsDetailState: MutableStateFlow<PetsState> = MutableStateFlow(PetsState.Loading)
    val petsDetailState: StateFlow<PetsState> = _petsDetailState

    init {
        viewModelScope.launch {
            val auth = appAuth.auth()
            when (auth) {
                AppUser.NotLogged -> _userDetailState.emit(UserState.NotLogged)
                AppUser.Logged -> loadUser()
            }
        }
    }

    private suspend fun loadUser() {
        appStore.loadUser(appAuth.userId()).collect { state ->
            val newState = when (state) {
                is State.Loading -> UserState.Loading
                is State.Success -> {
                    val detail = state.data
                    UserState.UserReady(detail.nick, detail.created)
                }
                is State.Failed -> UserState.Error
            }
            _userDetailState.emit(newState)
        }
    }

    fun loadPets() {
        viewModelScope.launch {
            appStore.loadPets(appAuth.userId()).collect { state ->
                val newState = when (state) {
                    is State.Loading -> PetsState.Loading
                    is State.Success -> if (state.data.isEmpty()) {
                        PetsState.Empty
                    } else {
                        PetsState.PetsReady(state.data)
                    }
                    is State.Failed -> PetsState.Error
                }
                _petsDetailState.emit(newState)
            }
        }
    }
}