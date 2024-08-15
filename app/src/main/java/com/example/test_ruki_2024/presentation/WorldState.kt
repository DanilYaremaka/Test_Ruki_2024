package com.example.test_ruki_2024.presentation

import com.example.test_ruki_2024.domain.entity.World

sealed interface WorldState {

    data object Initial : WorldState
    data class ShowWorld(val world: World) : WorldState
}