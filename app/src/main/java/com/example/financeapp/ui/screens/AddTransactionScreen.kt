package com.example.financeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.financeapp.data.Category
import com.example.financeapp.ui.theme.EmeraldGreen
import com.example.financeapp.viewmodel.FinanceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    viewModel: FinanceViewModel,
    onBack: () -> Unit,
) {
    var title by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(Category.OTHER) }
    var isExpense by remember { mutableStateOf(true) }
    var titleError by remember { mutableStateOf(false) }
    var amountError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Transaction", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Text("Transaction Type", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                FilterChip(
                    selected = !isExpense,
                    onClick = { isExpense = false },
                    label = { Text("📈 Income") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = EmeraldGreen.copy(alpha = 0.2f)
                    )
                )
                FilterChip(
                    selected = isExpense,
                    onClick = { isExpense = true },
                    label = { Text("📉 Expense") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.errorContainer
                    )
                )
            }
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    titleError = false
                },
                label = { Text("Title") },
                placeholder = { Text("e.g. Monthly Salary") },
                isError = titleError,
                supportingText = {
                    if (titleError) Text("Title cannot be empty")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = amountText,
                onValueChange = {
                    amountText = it
                    amountError = false
                },
                label = { Text("Amount") },
                placeholder = { Text("0.00") },
                isError = amountError,
                supportingText = {
                    if (amountError) Text("Enter a valid amount")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                prefix = { Text("$ ") }
            )

            Text("Category", style = MaterialTheme.typography.labelLarge)
            val categories = Category.values()
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                categories.chunked(3).forEach { rowItems ->
//                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                        rowItems.forEach { category ->
//                            FilterChip(
//                                selected = selectedCategory == category,
//                                onClick = { selectedCategory = category },
//                                label = { Text("${category.emoji} ${category.label}") },
//                                modifier = Modifier.weight(1f)
//                            )
//                        }
//                        // Fill remaining space if row has < 3 items
//                        repeat(3 - rowItems.size) {
//                            Spacer(modifier = Modifier.weight(1f))
//                        }
//                    }
//                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ── Submit Button ────────────────────────────────────────────────────
            Button(
                onClick = {
                    // INPUT VALIDATION before calling ViewModel
                    val amount = amountText.toDoubleOrNull()
                    titleError = title.isBlank()
                    amountError = amount == null || amount <= 0

                    if (!titleError && !amountError && amount != null) {
                        viewModel.addTransaction(
                            title = title.trim(),
                            amount = amount,
                            category = selectedCategory,
                            isExpense = isExpense
                        )
                        onBack() // Go back to previous screen after saving
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = EmeraldGreen
                )
            ) {
                Text(
                    text = "Save Transaction",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}