package com.example.test_ruki_2024.domain.repository

import com.example.test_ruki_2024.domain.entity.World

interface WorldRepository {

    fun createCell()

    fun createLife()

    fun terminateLife()

    fun getWorldInfo(): World

}