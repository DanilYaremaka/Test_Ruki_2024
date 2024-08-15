package com.example.test_ruki_2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.test_ruki_2024.ui.MainScreen
import com.example.test_ruki_2024.ui.theme.Test_Ruki_2024Theme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test_Ruki_2024Theme(darkTheme = false) {
                MainScreen(
                    viewModel = koinViewModel()
                )
            }
        }
    }
}