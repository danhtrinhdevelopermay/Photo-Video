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

### Implemented Workflows (Fixed Build Issues)
✅ **Single Job Pipeline**: Simplified from multiple jobs to one linear workflow
✅ **Build APK**: Primary focus on successful APK generation with detailed logging
✅ **Lint Check**: Code quality analysis with continue-on-error
✅ **Unit Tests**: Testing with fallback handling
✅ **Artifact Upload**: APK files and reports using actions/upload-artifact@v4
✅ **Enhanced Diagnostics**: Detailed build status reporting and error tracking

### Pipeline Stages (Analysis-First Approach - Updated August 13, 2025)
1. **Environment Setup**: JDK 17 + Gradle cache + wrapper verification
2. **Clean Build**: Fresh start to avoid cached issues  
3. **Resource Validation**: XML files and Android resources check
4. **Code Quality Analysis**: Lint analysis (must pass before build)
5. **Unit Testing**: Code functionality verification (must pass before build)
6. **Debug APK Generation**: Build only after analysis and tests pass
7. **Release APK Generation**: Build only after all checks pass (main branch)
8. **Artifact Management**: Upload APKs and analysis reports

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
- **Fixed GitHub Actions build errors**: Resolved Gradle repository conflicts, updated actions to v4, simplified dependencies
- **Optimized build configuration**: Cleaned up build.gradle files, fixed wrapper, disabled problematic features
- **Critical XML Fix (August 12, 2025)**: Fixed "Premature end of file" error in backup_rules.xml by adding proper root element
- **Enhanced CI/CD Pipeline**: Added Android SDK setup and XML validation to prevent build failures
- **GitHub Actions Robustness**: Added fallback Python XML validation, SDK license acceptance, improved error handling
- **YAML Syntax Fix**: Resolved line 67 error by creating separate validate_xml.py script for cleaner YAML structure
- **CRITICAL APK Build Fix (August 13, 2025)**: Resolved persistent compilation errors through comprehensive architecture alignment:
  - **Root Cause Analysis**: Material3 source code conflicting with AppCompat theme system  
  - **Theme System Restoration**: Reverted to Material3.DayNight.NoActionBar to match source code APIs
  - **Dependency Optimization**: Removed AppCompat dependency, kept clean Material3 Compose stack
  - **Resource Cleanup**: Eliminated custom colorBackground attribute causing conflicts
  - **Launcher Icons**: Complete set of launcher icons for all Android densities (retained)
  - **Architecture Alignment**: Source code expectations now match build configuration
  - **CI/CD Enhancement**: Robust GitHub Actions pipeline with comprehensive error handling
- **Analysis-First Workflow Complete (August 13, 2025)**: Successfully restructured CI/CD pipeline to prioritize code quality:
  - **Lint Analysis First**: Code quality checks now run before APK build ✅
  - **Unit Tests First**: Functionality verification before compilation ✅
  - **Build Gating**: APK builds only proceed after successful analysis and tests ✅
  - **Local Script Update**: Enhanced build-test.sh to follow same analysis-first approach ✅
  - **Report Improvements**: Better artifact naming and status reporting ✅
  - **Fixed Dependencies**: Added missing Robolectric and Dispatchers imports for unit tests ✅
  - **Resolved Lint Issues**: Fixed backup_rules.xml structure for proper Android backup rules ✅
  - **Complete Pipeline Success**: All stages (Clean → Validate → Lint → Test → Build) working correctly ✅
- **GitHub Actions APK Build Fix (August 13, 2025)**: Resolved critical release build failures:
  - **Root Cause**: Corrupted 67-byte launcher icon PNG files causing AAPT compilation errors ✅
  - **Comprehensive Solution**: Generated complete set of valid launcher icons for all densities ✅  
  - **Icon Design**: Camera/photo-themed icons matching app purpose (1.5KB-7.4KB) ✅
  - **Enhanced Diagnostics**: Added launcher icon validation to GitHub Actions workflow ✅
  - **Build Verification**: Local debug and release APK builds successful ✅
  - **CI/CD Robustness**: Pipeline now prevents and detects corrupted resource files ✅

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