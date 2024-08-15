package com.example.test_ruki_2024

import android.app.Application
import com.example.test_ruki_2024.di.worldModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WorldApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            modules(
                worldModule
            )
        }
    }
}