# iOS Style Media Viewer Android App

## Project Overview
This is a comprehensive Android application that replicates the iOS 18 Photos app interface with advanced visual effects including Gaussian blur and liquid glass effects inspired by iOS 26. The app provides a modern media viewing experience with gesture support, album organization, and sophisticated UI animations.

## Features Implemented

### Core Features
✅ iOS 18-style interface design with dark theme
✅ Media gallery with grid layout for photos and videos
✅ Album organization and browsing
✅ Advanced media viewer with zoom and pan gestures
✅ Gaussian blur background effects
✅ Liquid glass UI components with transparency effects
✅ Modern Material Design 3 integration
✅ Permission handling for media access (Android 13+ compatible)

### Visual Effects
✅ Blurred glass cards with gradient borders
✅ Liquid glass effect buttons and controls
✅ Gaussian blur backgrounds in media viewer
✅ iOS-style tab switching animations
✅ Auto-hiding controls with smooth fade transitions
✅ Glass overlay effects on media interaction

### Technical Architecture
✅ MVVM architecture with ViewModels
✅ Jetpack Compose UI framework
✅ Coroutines for asynchronous operations
✅ Media content resolver integration
✅ Glide for efficient image loading
✅ Navigation component setup
✅ Material Design 3 theming

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/mediaviewer/ios/
│   │   │   ├── MainActivity.kt
│   │   │   ├── data/
│   │   │   │   ├── MediaItem.kt
│   │   │   │   └── MediaRepository.kt
│   │   │   └── ui/
│   │   │       ├── theme/ (Colors, Typography, Theme)
│   │   │       ├── components/ (BlurredGlassCard, iOSTabRow, LiquidGlassEffect)
│   │   │       ├── gallery/ (GalleryScreen, GalleryViewModel)
│   │   │       └── viewer/ (MediaViewerActivity, MediaViewerScreen)
│   │   └── res/
│   │       ├── values/ (colors, strings, themes)
│   │       └── xml/ (backup_rules, data_extraction_rules)
│   ├── test/ (Unit tests)
│   └── androidTest/ (Instrumented tests)
├── build.gradle
└── proguard-rules.pro
```

## GitHub Actions CI/CD Pipeline

### Implemented Workflows
✅ **Lint Check**: Runs Android Lint to identify code issues
✅ **Unit Tests**: Executes all unit tests with coverage reporting
✅ **Instrumented Tests**: Runs UI tests on Android emulator
✅ **Code Analysis**: Static code analysis with security scanning
✅ **Build APK**: Builds both debug and release APKs
✅ **Security Scan**: OWASP dependency vulnerability checking
✅ **Release**: Automated releases with APK artifacts

### Pipeline Stages
1. **Code Quality**: Lint + Unit Tests
2. **Testing**: Instrumented tests on emulator
3. **Analysis**: Security and code quality scanning  
4. **Build**: APK generation for debug/release
5. **Release**: Automated GitHub releases with artifacts

## Dependencies Used

### Core Android
- Jetpack Compose (UI framework)
- Material Design 3
- Navigation Component
- ViewModel & LiveData
- Activity Compose integration

### Media & Graphics
- Glide (image loading with Compose integration)
- Glide Transformations (blur effects)
- Blurry library (additional blur effects)
- ExoPlayer (video playback)
- PhotoView (advanced image gestures)

### Testing
- JUnit 4 (unit testing)
- Mockito (mocking framework)
- Espresso (UI testing)
- Compose testing utilities

## User Preferences
- Language: Vietnamese (project request in Vietnamese)
- Interface Style: iOS-inspired with modern Android Material Design
- Effects: Advanced visual effects (Gaussian blur, liquid glass)
- Architecture: Clean architecture with MVVM pattern

## Recent Changes
- **August 12, 2025**: Complete Android project created from scratch
- Implemented full iOS-style interface with advanced visual effects
- Added comprehensive testing suite with unit and instrumented tests
- Configured GitHub Actions CI/CD pipeline with multi-stage builds
- Integrated modern Material Design 3 with custom iOS-inspired theming
- Added sophisticated gesture handling and media viewing capabilities

## Build Instructions

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 17+
- Android SDK API 34+
- Gradle 8.4+

### Local Build
```bash
./gradlew assembleDebug    # Build debug APK
./gradlew assembleRelease  # Build release APK
./gradlew test            # Run unit tests
./gradlew connectedAndroidTest  # Run instrumented tests
```

### GitHub Actions
The pipeline automatically builds APKs on push to main/develop branches and creates releases with downloadable APK files.

## Architecture Decisions

### UI Framework
- **Jetpack Compose**: Modern declarative UI for better performance and maintainability
- **Material Design 3**: Latest design system with dynamic theming support

### State Management  
- **ViewModel + StateFlow**: Reactive state management with lifecycle awareness
- **Repository Pattern**: Clean separation of data layer concerns

### Visual Effects Implementation
- **Custom Composables**: Reusable blur and glass effect components
- **Gradient Brushes**: Multi-layer gradients for depth and transparency
- **Animation APIs**: Smooth transitions and interactive feedback

### Testing Strategy
- **Unit Tests**: ViewModels and repository logic
- **Integration Tests**: Database and API interactions  
- **UI Tests**: User interaction flows and navigation

This project demonstrates modern Android development practices with sophisticated UI design inspired by iOS aesthetics while maintaining Android platform conventions.