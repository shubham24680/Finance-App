package com.example.financeapp.data

import com.example.financeapp.R
import java.util.UUID

data class OnboardingData(
    val image: Int,
    val header: String,
    val title: String,
    val desc: String,
)

val onboardingData = OnboardingData(
    R.drawable.onboarding_1,
    header = "Take Control",
    "of Your Finances Today!",
    "With out app, you can easily track your income and expenses, set financial goals, and make informed decisions about your money."
)

data class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val amount: Double,
    val category: Category,
    val date: String,
)

enum class Category(val label: String, val emoji: String) {
    FOOD("Food", "🍔"),
    TRANSPORT("Transport", "🚗"),
    SALARY("Salary", "💼"),
    SHOPPING("Shopping", "🛍️"),
    ENTERTAINMENT("Entertainment", "🎬"),
    HEALTH("Health", "💊"),
    OTHER("Other", "📦")
}