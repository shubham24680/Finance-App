package com.example.financeapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.financeapp.ui.screens.*
import com.example.financeapp.viewmodel.FinanceViewModel

object Routes {
    const val ONBOARDING = "onboarding"
    const val DASHBOARD = "dashboard"
    const val TRANSACTIONS = "transactions"
    const val ADD_TRANSACTION = "add_transaction"
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: FinanceViewModel = viewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = Routes.ONBOARDING
    ) {

        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onNavigateToHome = { navController.navigate(Routes.DASHBOARD) }
            )
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(
                viewModel = viewModel,
                onNavigateToTransactions = {
                    navController.navigate(Routes.TRANSACTIONS)
                },
                onNavigateToAdd = {
                    navController.navigate(Routes.ADD_TRANSACTION)
                }
            )
        }

        composable(Routes.TRANSACTIONS) {
            TransactionListScreen(
                viewModel = viewModel,
                onNavigateToAdd = {
                    navController.navigate(Routes.ADD_TRANSACTION)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ADD_TRANSACTION) {
            AddTransactionScreen(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}