package com.example.test_ruki_2024.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.test_ruki_2024.domain.entity.CellState
import com.example.test_ruki_2024.domain.entity.World
import com.example.test_ruki_2024.domain.usecase.CleanWorldUseCase
import com.example.test_ruki_2024.domain.usecase.CreateCellUseCase
import com.example.test_ruki_2024.domain.usecase.CreateLifeUseCase
import com.example.test_ruki_2024.domain.usecase.GetWorldInfoUseCase
import com.example.test_ruki_2024.domain.usecase.TerminateLifeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WorldViewModel(
    private val createCellUseCase: CreateCellUseCase,
    private val createLifeUseCase: CreateLifeUseCase,
    private val terminateLifeUseCase: TerminateLifeUseCase,
    private val getWorldInfoUseCase: GetWorldInfoUseCase,
    private val cleanWorldUseCase: CleanWorldUseCase
): ViewModel() {

    private val _state = MutableStateFlow<WorldState>(WorldState.Initial)
    val state: StateFlow<WorldState> = _state

    fun createCell() {
        createCellUseCase()

        val world = getWorldInfoUseCase()
        _state.value = WorldState.ShowWorld(world)

        checkStreak(world)
    }

    private fun checkStreak(world: World) {
        if (world.aliveStreak == 3) {
            createLifeUseCase()
            WorldState.ShowWorld(world)
        }

        val count = world.cells.count()
        if (count > 3) {
            if (world.deadStreak == 3 && world.cells[count - 4].state == CellState.LIFE) {
                terminateLifeUseCase()
                WorldState.ShowWorld(world)
            }
        }
    }

    fun clean() {
        if (_state.value != WorldState.Initial) {
            cleanWorldUseCase()
            _state.value = WorldState.Initial
        }
    }
}