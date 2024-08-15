package com.example.test_ruki_2024.domain.usecase

import com.example.test_ruki_2024.domain.repository.WorldRepository

class CleanWorldUseCase(private val repository: WorldRepository) {

    operator fun invoke() = repository.cleanWorld()
}