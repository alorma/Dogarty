package com.alorma.dogarty.ui.screens.user

import java.time.LocalDate

sealed class UserState {
    object Loading : UserState()
    object NotLogged: UserState()
    data class UserReady(val nick: String, val created: LocalDate) : UserState()
    object Error : UserState()
}