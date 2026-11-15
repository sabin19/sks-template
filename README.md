# SKS Kotlin Multiplatform Project Template

A production-ready, modular **Kotlin Multiplatform (KMP)** and **Compose Multiplatform (CMP)** project template targeting:
- 📱 **Android**
- 🍏 **iOS**
- 💻 **Desktop** (macOS, Windows, Linux via JVM)
- 🌐 **Web** (Kotlin/Wasm & Kotlin/JS)

---

## 🏗 Architecture & Modules

The project is structured according to **Clean Architecture** with strict modular boundaries and Gradle convention plugins.

```
sks-template/
├── androidApp/                     # Android application launcher (MainActivity, Application)
├── iosApp/                         # iOS Xcode project & SwiftUI host (iOSApp, ContentView)
├── desktopApp/                     # Desktop Compose launcher (macOS, Windows, Linux)
├── webApp/                         # Web Compose launcher (Kotlin/Wasm & Kotlin/JS with HTML shells)
├── shared/                         # App composition, navigation orchestration, and centralized DI
├── build-logic/                    # Gradle Convention Plugins (included build)
│   └── convention/
│       ├── sks.application.multiplatform
│       ├── sks.multiplatform.compose
│       └── sks.application.feature
├── feature/                        # User-facing features
│   └── onboarding/                 # Starter feature module (MVI/MVVM with UseCase & ViewModel)
└── core/                           # Reusable core modules
    ├── common/                     # Dispatchers (Main, IO, Default), Result<T>, AppException
    ├── model/                      # Shared data models & Kotlinx Serialization DTOs
    ├── network/                    # Ktor 3 Client (OkHttp for Android/Desktop, Darwin for iOS, Js for Web)
    ├── datastore/                  # Multiplatform AndroidX DataStore (POSIX files for Mobile/Desktop, WebLocalStorage for Web)
    ├── data/                       # Repository interfaces & implementations
    ├── domain/                     # Coroutine & Flow UseCases, business logic
    ├── designsystem/               # Material 3 Compose theme, color schemes, typography
    ├── ui/                         # Reusable UI components, Coil 3 image loading, scaffolds
    └── permission/                 # Multiplatform permission abstraction service
```

---

## 🚀 Convention Plugins (`build-logic`)

Build configuration across modules is centralized via 3 convention plugins:

1. **`sks.application.multiplatform`**
   - Configures targets: `androidTarget()`, `jvm("desktop")`, `iosArm64()`, `iosSimulatorArm64()`, `js { browser() }`, and `wasmJs { browser() }`.
   - Sets Java 21 toolchain and Android library SDK (`compileSdk 37`, `minSdk 24`).
   - Automatically determines Android namespace (`com.sks.template.<module-path>`).
   - Injects core coroutines (`core`, `android`, `swing`), datetime, and Koin base dependencies.

2. **`sks.multiplatform.compose`**
   - Applies JetBrains Compose and Compose Compiler plugins.
   - Injects Compose runtime, UI, Material 3, icons, components resources, navigation, and `koin-compose`.

3. **`sks.application.feature`**
   - Applies Kotlinx Serialization.
   - Automatically connects standard feature dependencies: `:core:designsystem`, `:core:ui`, `:core:domain`, `:core:common`, `:core:model`.

---

## 🛠 Tech Stack & Dependencies

- **Language**: Kotlin 2.4.x / 2.1.x
- **UI Toolkit**: Compose Multiplatform (Material 3)
- **Dependency Injection**: Koin 4.2.2 (`koin-core`, `koin-android`, `koin-compose`, `koin-compose-viewmodel`)
- **Networking**: Ktor 3.6.1 (OkHttp for Android/Desktop, Darwin for iOS, Js engine for Web)
- **Image Loading**: Coil 3.6.3 (with Ktor 3 network fetcher)
- **Local Storage**: AndroidX DataStore 1.3.0 (`datastore-preferences-core`, `datastore-core-okio`)
- **Concurrency**: Kotlinx Coroutines 1.11.0 (`core`, `android`, `swing`)
- **Serialization & Datetime**: Kotlinx Serialization 1.13.0, Kotlinx DateTime 0.8.0

---

## 🏃 Running the Application

### 📱 Android
- **Via Android Studio:** Select the **`androidApp`** run configuration and click **Run** (▶️).
- **Via Terminal (Install to connected device/emulator):**
  ```bash
  ./gradlew :androidApp:installDebug
  ```

---

### 🍏 iOS
- **Via Xcode:** Open `iosApp/iosApp.xcodeproj` in Xcode and click **Run** targeting an iOS Simulator or Device.
- **Via Android Studio:** Select the **`iosApp`** run configuration and click **Run** (requires Kotlin Multiplatform Mobile plugin).

---

### 💻 Desktop (macOS, Windows, Linux)
- **Via Android Studio:** Run the **`:desktopApp:run`** task.
- **Via Terminal:**
  ```bash
  ./gradlew :desktopApp:run
  ```

---

### 🌐 Web (Kotlin/Wasm & Kotlin/JS)

#### WebAssembly (Wasm) Dev Server (Recommended)
Starts hot-reloading development server on `http://localhost:8080/`:
```bash
./gradlew :webApp:wasmJsBrowserDevelopmentRun --continuous
```

#### JavaScript (JS) Dev Server
Starts hot-reloading development server on `http://localhost:8080/`:
```bash
./gradlew :webApp:jsBrowserDevelopmentRun --continuous
```

---

## 📦 Making Production Distributions & Releases

### 📱 Android
- **Release APK:**
  ```bash
  ./gradlew :androidApp:assembleRelease
  ```
  *Output:* `androidApp/build/outputs/apk/release/androidApp-release-unsigned.apk`
- **Release Android App Bundle (.aab for Google Play):**
  ```bash
  ./gradlew :androidApp:bundleRelease
  ```
  *Output:* `androidApp/build/outputs/bundle/release/androidApp-release.aab`

---

### 🍏 iOS
- **Build Release XCFramework:**
  ```bash
  ./gradlew :shared:assembleSharedReleaseXCFramework
  ```
- **Archive for App Store / TestFlight:**
  Open `iosApp/iosApp.xcodeproj` in Xcode $\rightarrow$ **Product $\rightarrow$ Archive**.

---

### 💻 Desktop

Compose for Desktop packages native binaries using the host OS packaging tools.

- **Runnable Standalone JAR (Cross-platform):**
  ```bash
  ./gradlew :desktopApp:packageUberJarForCurrentOS
  ```
  *Output:* `desktopApp/build/compose/jars/desktopApp-*.jar`
- **macOS Installer (.dmg):**
  ```bash
  ./gradlew :desktopApp:packageDmg
  ```
  *Output:* `desktopApp/build/compose/binaries/main/dmg/`
- **Windows Installer (.msi):**
  ```bash
  ./gradlew :desktopApp:packageMsi
  ```
  *Output:* `desktopApp/build/compose/binaries/main/msi/`
- **Linux Package (.deb):**
  ```bash
  ./gradlew :desktopApp:packageDeb
  ```
  *Output:* `desktopApp/build/compose/binaries/main/deb/`

---

### 🌐 Web (Production Bundles)

Compiles and optimizes minified Webpack bundles and static assets ready for deployment to any web host (Firebase Hosting, Cloudflare Pages, Vercel, Netlify, Nginx, S3):

- **WebAssembly (Wasm) Production Distribution:**
  ```bash
  ./gradlew :webApp:wasmJsBrowserDistribution
  ```
  *Output Directory:* `webApp/build/dist/wasmJs/productionExecutable/`
  *(Includes `index.html`, `webApp.js`, and `webApp.wasm`)*

- **JavaScript (JS) Production Distribution:**
  ```bash
  ./gradlew :webApp:jsBrowserDistribution
  ```
  *Output Directory:* `webApp/build/dist/js/productionExecutable/`
  *(Includes `index.html` and minified `webApp.js`)*

---

## 🧪 Testing & Verification

To verify that all platforms compile cleanly without opening the respective emulators or browsers:

```bash
# Compile all Desktop sources
./gradlew :desktopApp:compileKotlinDesktop

# Compile all Web sources
./gradlew :webApp:compileKotlinWasmJs :webApp:compileKotlinJs

# Compile all Android sources
./gradlew :androidApp:assembleDebug

# Compile all iOS framework sources
./gradlew :shared:compileKotlinIosSimulatorArm64
```
