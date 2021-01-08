package com.alorma.dogarty.ui.model

sealed class AppUser {
    object NotLogged : AppUser()
    object Logged : AppUser()
}