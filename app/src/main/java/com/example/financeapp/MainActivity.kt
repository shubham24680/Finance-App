package com.example.financeapp

import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import com.example.financeapp.navigation.AppNavGraph
import com.example.financeapp.ui.theme.FinanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanceAppTheme {
                AppNavGraph()
            }
        }
    }
}