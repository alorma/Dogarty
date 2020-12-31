package com.alorma.dogarty.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class PetDetail(
    val id: String,
    val name: String,
    val birthDate: LocalDate,
    val created: LocalDateTime
)