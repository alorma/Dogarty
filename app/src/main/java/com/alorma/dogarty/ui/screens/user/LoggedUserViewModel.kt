package com.alorma.dogarty.ui.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alorma.dogarty.auth.AppAuth
import com.alorma.dogarty.domain.model.State
import com.alorma.dogarty.store.AppStore
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

        viewModelScope.launch {
            appStore.loadPets(appAuth.userId()).collect { state ->
                val newState = when (state) {
                    is State.Loading -> PetsState.Loading
                    is State.Success -> PetsState.PetsReady(state.data)
                    is State.Failed -> PetsState.Error
                }
                _petsDetailState.emit(newState)
            }
        }
    }
}