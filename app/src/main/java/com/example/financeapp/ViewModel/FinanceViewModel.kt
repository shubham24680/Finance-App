package com.example.financeapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.financeapp.data.*
import kotlinx.coroutines.flow.*

data class FinanceUiState(
    val transactions: List<Transaction> = emptyList(),
    val isLoading: Boolean = false,
)

class FinanceViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FinanceUiState())
    val uiState: StateFlow<FinanceUiState> = _uiState.asStateFlow()

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        _uiState.update { currentState ->
            currentState.copy(
                transactions = TransactionRepository.getAll()
            )
        }
    }

    fun addTransaction(
        title: String,
        amount: Double,
        category: Category,
        isExpense: Boolean,
    ) {
        val finalAmount = if (isExpense) -amount else amount

        val newTransaction = Transaction(
            title = title,
            amount = finalAmount,
            category = category,
            date = "2025-02-24"
        )

        TransactionRepository.add(newTransaction)
        loadTransactions()
    }

    fun deleteTransaction(id: String) {
        TransactionRepository.delete(id)
        loadTransactions()
    }

    fun getTotalBalance(): Double =
        _uiState.value.transactions.sumOf { it.amount }

    fun getTotalIncome(): Double =
        _uiState.value.transactions
            .filter { it.amount > 0 }
            .sumOf { it.amount }

    fun getTotalExpenses(): Double =
        _uiState.value.transactions
            .filter { it.amount < 0 }
            .sumOf { it.amount }
}