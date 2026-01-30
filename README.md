<div align="center">

# 🛍️ ProductApp - Modern Android Development

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Material Design](https://img.shields.io/badge/Material%20Design%203-757575?style=for-the-badge&logo=materialdesign&logoColor=white)

**E-commerce Android app showcasing modern development practices**

*MVVM • Clean Architecture • Jetpack Compose • Hilt*

[🚀 Features](#-features) • [🏗 Architecture](#-architecture) • [🛠 Tech Stack](#️-tech-stack) • [📦 Installation](#-installation)

</div>

---

## 📱 About

**ProductApp** is a learning-focused Android application built with **Kotlin** and **Jetpack Compose**. This project demonstrates modern Android development best practices, including:

- 🎯 **Clean Architecture** principles
- 🔄 **Reactive programming** with StateFlow
- 💉 **Dependency Injection** with Hilt
- 🎨 **Modern UI** with Jetpack Compose
- 🌐 **REST API** consumption

> 📌 This is an educational project developed step-by-step with incremental commits and well-documented architectural decisions.

---

## ✨ Features

### 🛒 Core Functionality
- ✅ **Product Listing** - Browse products from FakeStore API
- ✅ **Image Loading** - Efficient image caching with Coil
- ✅ **Pull to Refresh** - Update product list
- ✅ **Error Handling** - Graceful error states with retry mechanism
- ✅ **Loading States** - Smooth UX with loading indicators

### 🎨 User Interface
- ✅ **Material Design 3** - Modern, beautiful UI components
- ✅ **Responsive Layout** - Adapts to different screen sizes
- ✅ **Dark Theme Support** - System-based theme switching
- ✅ **Smooth Animations** - Native Compose animations

### 🔧 Technical Features
- ✅ **State Management** - Predictable UI states (Loading/Success/Error)
- ✅ **Reactive UI** - StateFlow-based reactive updates
- ✅ **Type-Safe Navigation** - Compose Navigation (planned)
- ✅ **Offline Support** - Room caching (planned)

---

## 🏗 Architecture

### MVVM + Clean Architecture (Base)

The project follows a clean, scalable architecture pattern:

```
┌─────────────────────────────────────────┐
│         PRESENTATION LAYER              │
│    (Compose UI + ViewModels)            │
├─────────────────────────────────────────┤
│         DOMAIN LAYER                    │
│         (Use Cases - Planned)           │
├─────────────────────────────────────────┤
│           DATA LAYER                    │
│     (Repository + Data Sources)         │
├─────────────────────────────────────────┤
│         EXTERNAL SERVICES               │
│      (Retrofit + FakeStore API)         │
└─────────────────────────────────────────┘
```

### 📂 Project Structure

```
app/
├── ui/
│   ├── screen/
│   │   └── ProductListScreen.kt      → Main product screen
│   ├── components/
│   │   ├── ProductCard.kt            → Reusable product card
│   │   ├── LoadingIndicator.kt       → Loading state UI
│   │   └── ErrorView.kt              → Error state UI
│   ├── state/
│   │   └── UiState.kt                → Sealed class for UI states
│   └── viewmodel/
│       └── ProductViewModel.kt       → Business logic & state
│
├── data/
│   ├── model/
│   │   └── Product.kt                → Data models
│   ├── repository/
│   │   └── ProductRepository.kt      → Single source of truth
│   └── remote/
│       └── ApiService.kt             → Retrofit API interface
│
└── di/
    └── NetworkModule.kt              → Hilt DI configuration
```

### 🔄 Data Flow

```
┌──────────┐      ┌──────────────┐      ┌────────────┐      ┌─────────┐
│   UI     │ ───> │  ViewModel   │ ───> │ Repository │ ───> │   API   │
│ (Compose)│ <─── │ (StateFlow)  │ <─── │  (Single   │ <─── │(Retrofit)│
└──────────┘      └──────────────┘      │   Source)  │      └─────────┘
                                        └────────────┘
```

1. **UI** observes ViewModel's StateFlow
2. **ViewModel** requests data from Repository
3. **Repository** fetches from API (or cache - future)
4. **Data** flows back as UiState (Loading → Success/Error)

---

## 🧠 State Management

### UiState Pattern

The app uses a **sealed class** to represent all possible UI states:

```kotlin
sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
```

### Benefits
- ✅ **Type-safe** - Compiler guarantees all states are handled
- ✅ **Predictable** - UI always reflects current state
- ✅ **Testable** - Easy to unit test state transitions
- ✅ **Maintainable** - Clear separation of concerns

### State Flow Example

```kotlin
// ViewModel
private val _uiState = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
val uiState: StateFlow<UiState<List<Product>>> = _uiState.asStateFlow()

// UI (Compose)
val state by viewModel.uiState.collectAsState()

when (state) {
    is UiState.Loading -> LoadingIndicator()
    is UiState.Success -> ProductList(state.data)
    is UiState.Error -> ErrorView(state.message, onRetry = { viewModel.retry() })
}
```

---

## 🛠️ Tech Stack

### Core Technologies

```kotlin
Kotlin: 1.9.0+
├── Coroutines - Asynchronous programming
└── Flow/StateFlow - Reactive streams

Android
├── compileSdk: 34
├── minSdk: 24 (Android 7.0)
└── targetSdk: 34
```

### Jetpack Libraries

```gradle
Jetpack Compose
├── Compose UI: 1.5.4
├── Compose Material3: 1.1.2
├── Compose Navigation: 2.7.5 (planned)
└── Compose Runtime

AndroidX
├── Core KTX: 1.12.0
├── Lifecycle ViewModel: 2.6.2
├── Activity Compose: 1.8.1
└── Lifecycle Runtime: 2.6.2
```

### Networking & Data

```gradle
Retrofit: 2.9.0
├── Converter Gson - JSON parsing
└── OkHttp - HTTP client

Image Loading
└── Coil Compose: 2.5.0
```

### Dependency Injection

```gradle
Hilt: 2.48
├── Hilt Android
└── Hilt Compiler
```

### API

```
FakeStore API
└── https://fakestoreapi.com
    ├── GET /products - List all products
    └── GET /products/{id} - Product details (planned)
```

---

## 📦 Installation

### Prerequisites

```bash
# Required versions
- Android Studio: Hedgehog | 2023.1.1 or newer
- JDK: 17 or higher
- Kotlin: 1.9.0+
- Gradle: 8.0+
```

### Setup Instructions

1. **Clone the repository**
```bash
git clone https://github.com/elvynedinson/ProductApp.git
cd ProductApp
```

2. **Open in Android Studio**
```bash
# File → Open → Select ProductApp folder
```

3. **Sync Gradle**
```bash
# Android Studio will automatically sync
# Or manually: File → Sync Project with Gradle Files
```

4. **Run the app**
```bash
# Select device/emulator
# Click Run button or Shift+F10
```

### Build Variants

```bash
# Debug build
./gradlew assembleDebug

# Release build (requires signing config)
./gradlew assembleRelease

# Install on device
./gradlew installDebug
```

---

## 🎯 Best Practices Implemented

### Code Quality
- ✅ **Single Responsibility** - Each class has one clear purpose
- ✅ **Dependency Inversion** - Depend on abstractions, not implementations
- ✅ **Immutability** - Data classes are immutable
- ✅ **Type Safety** - Leveraging Kotlin's type system

### Architecture
- ✅ **Separation of Concerns** - UI, Business Logic, Data are separated
- ✅ **Unidirectional Data Flow** - Data flows in one direction
- ✅ **Single Source of Truth** - Repository pattern
- ✅ **Reactive UI** - State-driven UI updates

### Development
- ✅ **Incremental Development** - Small, focused commits
- ✅ **Descriptive Commits** - Clear commit messages
- ✅ **Code Comments** - Explaining "why", not "what"
- ✅ **Kotlin Conventions** - Following official style guide

### Performance
- ✅ **Efficient Recomposition** - Using `remember` and `derivedStateOf`
- ✅ **Image Caching** - Coil handles caching automatically
- ✅ **Coroutine Scoping** - Proper lifecycle-aware coroutines
- ✅ **State Hoisting** - Reusable, stateless composables

---

### Current State
The project is being built with testability in mind, though tests are not yet implemented.

---

## 📚 Learning Resources

This project was built following industry best practices and official documentation:

- [Android Developer Guides](https://developer.android.com/guide)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- [Clean Architecture by Uncle Bob](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Google's Architecture Samples](https://github.com/android/architecture-samples)

---

## 🤝 Contributing

This is a personal learning project, but suggestions and feedback are welcome!

If you want to contribute:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 👨‍💻 Developer

**Elvyn Edinson**

Android Developer in training 🚀

- GitHub: [@elvynedinson](https://github.com/elvynedinson)
- LinkedIn: [Elvyn Edinson](https://www.linkedin.com/in/elvyn-paucar-ponce/)
- Email: elvyn.paucar.ponce@gmail.com

### Skills Demonstrated
- ✅ Kotlin
- ✅ Jetpack Compose
- ✅ MVVM Architecture
- ✅ Clean Architecture Principles
- ✅ Dependency Injection (Hilt)
- ✅ Reactive Programming (Coroutines, Flow)
- ✅ REST API Integration
- ✅ Material Design 3

---

## 🙏 Acknowledgments

- **FakeStore API** for providing the free REST API
- **Android Community** for excellent documentation and support
- **JetBrains** for Kotlin and IntelliJ IDEA
- **Google** for Android and Jetpack libraries

---

## 📊 Project Stats

```
Lines of Code: ~500 (and growing)
Commits: Incremental and descriptive
Architecture: MVVM + Clean Architecture
UI Framework: 100% Jetpack Compose
```

---

<div align="center">

### ⭐ If you find this project helpful, consider giving it a star!

**Built with ❤️ using Kotlin + Jetpack Compose**

*Learning by doing, following real
