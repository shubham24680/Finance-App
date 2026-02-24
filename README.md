# 💰 Finance Tracker App
### A Beginner-Friendly Android Kotlin + Jetpack Compose Project

---

## 📱 What Does This App Do?

A personal finance tracker that lets you:
- **View your total balance**, income, and expenses on a dashboard
- **Add transactions** with a title, amount, and category
- **Delete transactions** with a single tap
- **Browse all transactions** on a dedicated screen

---

## 🧠 What You'll Learn

This project is designed to teach you 4 key Android development concepts:

| Concept | Where to Look |
|---|---|
| **State Management** | `FinanceViewModel.kt`, `AddTransactionScreen.kt` |
| **UI Components** | `TransactionCard.kt`, all screen files |
| **Navigation** | `NavGraph.kt` |
| **ViewModel & Architecture** | `FinanceViewModel.kt`, `TransactionRepository.kt` |

---

## 📁 Project Structure

```
app/src/main/java/com/example/financeapp/
│
├── data/
│   ├── Transaction.kt          ← Data model (what a transaction looks like)
│   └── TransactionRepository.kt ← Data source (where data is stored/fetched)
│
├── viewmodel/
│   └── FinanceViewModel.kt     ← Business logic + state management
│
├── navigation/
│   └── NavGraph.kt             ← Screen routes and navigation map
│
├── ui/
│   ├── theme/
│   │   └── Theme.kt            ← Colors, typography, Material3 theme
│   ├── components/
│   │   └── TransactionCard.kt  ← Reusable UI components
│   └── screens/
│       ├── DashboardScreen.kt       ← Home screen with balance summary
│       ├── TransactionListScreen.kt ← Full transaction history
│       └── AddTransactionScreen.kt  ← Form to add new transactions
│
└── MainActivity.kt             ← App entry point
```

---

## 🚀 How to Set Up

### Prerequisites
- **Android Studio Hedgehog** (2023.1.1) or newer
- **JDK 11** or higher
- An Android device or emulator running **API 26+** (Android 8.0+)

### Steps

1. **Open Android Studio** → File → New → Project from Version Control
    - Or: File → Open → navigate to this folder

2. **Wait for Gradle sync** to complete (this downloads all dependencies)

3. **Run the app** by pressing the ▶️ green play button
    - Choose your emulator or connected device

> 💡 **First time?** Android Studio will prompt you to create an emulator.
> Go to Tools → Device Manager → Create Device → choose "Pixel 6" → Android 14.

---

## 🔑 Key Concepts Explained

### 1. State Management

```kotlin
// In ViewModel — the SINGLE source of truth
private val _uiState = MutableStateFlow(FinanceUiState())
val uiState: StateFlow<FinanceUiState> = _uiState.asStateFlow()

// In Composable — observing state
val uiState by viewModel.uiState.collectAsState()
// ↑ This line makes the UI re-draw whenever state changes!
```

**Local state** (inside a composable, for UI-only things like text field values):
```kotlin
var title by remember { mutableStateOf("") }
```

**ViewModel state** (shared across screens, survives rotation):
```kotlin
_uiState.update { it.copy(transactions = newList) }
```

---

### 2. Jetpack Compose UI

Compose is **declarative** — you describe *what* the UI should look like, not *how* to change it:

```kotlin
// XML (old way) — imperative
textView.text = "Hello"
textView.setTextColor(Color.RED)

// Compose (new way) — declarative
Text(
    text = "Hello",
    color = Color.Red
)
```

---

### 3. Navigation

```kotlin
// Define routes
object Routes {
    const val DASHBOARD = "dashboard"
    const val ADD_TRANSACTION = "add_transaction"
}

// Navigate to a screen
navController.navigate(Routes.ADD_TRANSACTION)

// Go back
navController.popBackStack()
```

---

### 4. ViewModel Architecture

```
UI (Composables)
    ↕ observes state / calls functions
ViewModel
    ↕ reads / writes data
Repository
    ↕ (in real apps) talks to
Database / Network API
```

The ViewModel is the middleman — the UI never touches data directly.

---

## 🛠️ Extending This App (Next Steps)

Once you're comfortable, try these challenges:

1. **Add a Room database** so data persists between app restarts
2. **Add a date picker** using `DatePickerDialog`
3. **Add charts** using the [Vico](https://github.com/patrykandpatrick/vico) library
4. **Filter transactions** by category
5. **Add a monthly budget** with a progress bar showing spending vs limit

---

## 📦 Dependencies Used

| Library | Purpose |
|---|---|
| `Jetpack Compose` | Modern UI toolkit — replaces XML layouts |
| `Material 3` | Google's design system (buttons, cards, colors) |
| `Navigation Compose` | Screen-to-screen navigation |
| `ViewModel + StateFlow` | State management that survives rotation |
| `Material Icons Extended` | 900+ ready-to-use icons |

---

## 💡 Tips for Beginners

- **Recomposition**: Compose re-runs your composable function whenever state changes. Don't be afraid of this — it's fast and intentional.
- **`remember`**: Keeps a value alive across recompositions. Without it, variables reset every time.
- **`Modifier`**: Think of it like CSS for Compose — controls size, padding, clicks, backgrounds, etc.
- **Preview**: Add `@Preview @Composable fun MyScreenPreview()` to see your UI without running the app.

---

*Happy coding! 🚀*