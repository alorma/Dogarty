package com.alorma.dogarty.ui.model

sealed class AppUser {
    object Loading : AppUser()
    object NotLogged : AppUser()
    object Logged : AppUser()
}