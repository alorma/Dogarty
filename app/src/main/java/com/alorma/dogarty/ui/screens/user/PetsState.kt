package com.alorma.dogarty.ui.screens.user

import com.alorma.dogarty.domain.model.PetDetail

sealed class PetsState {
    object Loading : PetsState()
    object Empty : PetsState()
    data class PetsReady(val items: List<PetDetail>) : PetsState()
    object Error : PetsState()
}