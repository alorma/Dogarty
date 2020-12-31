package com.alorma.dogarty.domain.model

import java.time.LocalDate

data class UserDetail(
    val nick: String,
    val created: LocalDate
)