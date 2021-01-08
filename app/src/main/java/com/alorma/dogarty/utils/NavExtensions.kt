package com.alorma.dogarty.utils

import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.alorma.dogarty.ui.Navigation

fun NavController.navToLogin() {
    navigate(Navigation.ROUTE_LOGIN) {
        launchSingleTop = true
    }
}
fun NavController.navToUser() {
    navigate(Navigation.ROUTE_USER)
}