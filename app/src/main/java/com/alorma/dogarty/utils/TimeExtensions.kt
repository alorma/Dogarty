package com.alorma.dogarty.utils

import com.google.firebase.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId

fun Timestamp.toLocalDateTime(): LocalDateTime = LocalDateTime.ofInstant(
    toDate().toInstant(),
    ZoneId.systemDefault()
)