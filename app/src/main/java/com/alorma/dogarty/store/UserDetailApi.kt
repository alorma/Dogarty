package com.alorma.dogarty.store

import com.google.firebase.Timestamp

data class UserDetailApi(
    val nick: String = "",
    val created_at: Timestamp? = null
)