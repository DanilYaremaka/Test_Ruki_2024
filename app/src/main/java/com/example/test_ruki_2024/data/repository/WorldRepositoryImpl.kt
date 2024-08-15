package com.example.test_ruki_2024.data.repository

import com.example.test_ruki_2024.domain.entity.Cell
import com.example.test_ruki_2024.domain.entity.CellState
import com.example.test_ruki_2024.domain.entity.World
import com.example.test_ruki_2024.domain.repository.WorldRepository
import kotlin.random.Random

class WorldRepositoryImpl : WorldRepository {

    private val cells = mutableListOf<Cell>()
    private var aliveStreak = 0
    private var deadStreak = 0

    override fun createCell() {
        if (Random.nextBoolean()) {
            aliveStreak++
            deadStreak = 0
            cells.add(Cell(CellState.ALIVE))
        }
        else {
            deadStreak++
            aliveStreak = 0
            cells.add(Cell(CellState.DEAD))
        }
    }

    override fun createLife() {
        aliveStreak = 0
        deadStreak = 0
        cells.add(Cell(CellState.LIFE))
    }

    override fun terminateLife() {
        val lastLife = getNearestLifeId()
        if (lastLife != UNDEFINED)
            cells[lastLife].state = CellState.TERMINATED
    }

    override fun getWorldInfo(): World {
        return World(cells, aliveStreak, deadStreak)
    }

    private fun getNearestLifeId(): Int {
        var lastLife = UNDEFINED
        cells.forEachIndexed { index, cell ->
            if (cell.state == CellState.LIFE)
                lastLife = index
        }
        return lastLife
    }

    companion object {
        const val UNDEFINED = -1
    }
}