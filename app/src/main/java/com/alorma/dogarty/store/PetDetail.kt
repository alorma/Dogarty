package com.alorma.dogarty.store

import com.google.firebase.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime

data class PetDetailApi(
    val id: String = "",
    val name: String = "",
    val birthdate: Timestamp ? = null,
    val created_at: Timestamp? = null
)