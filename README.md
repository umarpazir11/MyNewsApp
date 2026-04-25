# MyNewsApp

An Android news application that fetches and displays top headlines from [NewsAPI.org](https://newsapi.org), with infinite scroll pagination and a detail view for each article.

## Features

- Paginated list of top English headlines (21 articles per page)
- Adaptive grid layout: 2 columns in portrait, 3 columns in landscape
- Every 7th article spans the full width for visual variety
- Relative publication time (e.g. "2h ago", "3d ago")
- Article detail screen with title and full content
- Internet connectivity check with Snackbar feedback
- Image loading with fade-in transition via Glide

## Architecture

The app follows MVVM + Repository pattern with clean separation of concerns:

```
UI Layer       → Fragments + ViewModels (data binding)
Data Layer     → Repository + PageKeyedDataSource (Paging)
Network Layer  → Retrofit + OkHttp (NewsAPI)
DI             → Dagger 2 (component/module/inject)
```

**Key libraries:**

| Library | Purpose |
|---|---|
| Retrofit 2 + OkHttp 3 | HTTP client & REST |
| Dagger 2 | Dependency injection |
| AndroidX Paging 2 | Infinite scroll pagination |
| AndroidX Navigation | Single-activity navigation |
| Glide 4 | Image loading |
| Kotlin Coroutines | Async network calls |
| AndroidX Data Binding | View binding |

## Project Structure

```
app/src/main/java/com/test/mynewsapp/
├── api/                  # Retrofit service & result wrapper
├── binding/              # Data binding adapters (Glide)
├── di/                   # Dagger components, modules, factory
├── ui/
│   ├── data/             # Repository, DataSource, models
│   │   └── model/        # Article, Source, HeadLineNews
│   ├── headlines/        # HeadLinesFragment + Adapter + ViewModel
│   └── headlinesdetails/ # DetailsFragment + ViewModel
└── util/                 # ConnectivityUtil, ViewExtensions
```

## Setup

### 1. Get a NewsAPI key

Register for a free API key at [https://newsapi.org/register](https://newsapi.org/register).

### 2. Add your API key

Open `app/build.gradle` and replace the placeholder:

```groovy
buildConfigField 'String', 'NEWS_API_KEY', '"YOUR_API_KEY_HERE"'
```

> **Security note:** Do not commit a real API key to version control. Consider loading it from a local `secrets.properties` file excluded via `.gitignore`.

### 3. Build and run

Open the project in Android Studio (Arctic Fox or later) and run on a device or emulator running **Android 4.1+ (API 16)**.

```bash
./gradlew assembleDebug
```

## Requirements

- Android Studio Arctic Fox (2020.3.1) or later
- Android SDK 29 (compile/target)
- Minimum Android 4.1 (API 16)
- Internet connection (live data only, no offline cache)

## Known Limitations

- No offline caching — requires an active internet connection
- No search or category filtering
- Details screen shows only title and content; full article opens in-app WebView not yet implemented
- Free NewsAPI keys are limited to 100 requests/day and developer use only

## Running Tests

```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest
```

Unit tests cover the `NewsService` (MockWebServer), `HeadLinesRepository`, and `HeadLinesViewModel` using Mockito and `InstantTaskExecutorRule`.