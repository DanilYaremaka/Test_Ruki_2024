package com.example.test_ruki_2024.di

import com.example.test_ruki_2024.data.repository.WorldRepositoryImpl
import com.example.test_ruki_2024.domain.repository.WorldRepository
import com.example.test_ruki_2024.domain.usecase.CreateCellUseCase
import com.example.test_ruki_2024.domain.usecase.CreateLifeUseCase
import com.example.test_ruki_2024.domain.usecase.GetWorldInfoUseCase
import com.example.test_ruki_2024.domain.usecase.TerminateLifeUseCase
import com.example.test_ruki_2024.domain.usecase.CleanWorldUseCase
import com.example.test_ruki_2024.presentation.WorldViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val worldModule = module {

    singleOf(::WorldRepositoryImpl) bind WorldRepository::class

    factoryOf(::CreateCellUseCase)
    factoryOf(::GetWorldInfoUseCase)
    factoryOf(::CreateLifeUseCase)
    factoryOf(::TerminateLifeUseCase)
    factoryOf(::CleanWorldUseCase)

    viewModelOf(::WorldViewModel)
}