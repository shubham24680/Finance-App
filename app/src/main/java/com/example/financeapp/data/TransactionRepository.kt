package com.example.financeapp.data

object TransactionRepository {
    private val sampleTransactions = mutableListOf(
        Transaction(
            title = "Monthly Salary",
            amount = 3500.00,
            category = Category.SALARY,
            date = "2025-02-01"
        ),
        Transaction(
            title = "Grocery Shopping",
            amount = -85.50,
            category = Category.FOOD,
            date = "2025-02-03"
        ),
        Transaction(
            title = "Uber Ride",
            amount = -12.00,
            category = Category.TRANSPORT,
            date = "2025-02-05"
        ),
        Transaction(
            title = "Netflix Subscription",
            amount = -15.99,
            category = Category.ENTERTAINMENT,
            date = "2025-02-07"
        ),
        Transaction(
            title = "Freelance Project",
            amount = 450.00,
            category = Category.SALARY,
            date = "2025-02-10"
        ),
        Transaction(
            title = "New Shoes",
            amount = -79.99,
            category = Category.SHOPPING,
            date = "2025-02-12"
        ),
    )

    fun getAll(): List<Transaction> = sampleTransactions.toList()

    fun add(transaction: Transaction) {
        sampleTransactions.add(0, transaction) // Add to top of list
    }

    fun delete(id: String) {
        sampleTransactions.removeAll { it.id == id }
    }
}