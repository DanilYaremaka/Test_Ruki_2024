package com.example.test_ruki_2024.domain.entity

data class World(
    val cells: MutableList<Cell> = mutableListOf(),
    val aliveStreak: Int = 0,
    val deadStreak: Int = 0
)
