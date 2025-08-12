# iOS Style Media Viewer - Android App

A modern Android application that brings the elegant iOS 18 Photos interface to Android, featuring advanced visual effects including Gaussian blur and liquid glass effects inspired by iOS 26.

## 🌟 Key Features

### Visual Design
- **iOS 18 Interface**: Authentic iOS Photos app experience on Android
- **Gaussian Blur Effects**: Advanced blur backgrounds and overlays
- **Liquid Glass UI**: Transparent, glass-like interface components
- **Material Design 3**: Modern Android design integrated with iOS aesthetics
- **Dark Theme**: Optimized for modern viewing preferences

### Media Capabilities
- **Photo & Video Gallery**: Browse all media with grid layout
- **Album Organization**: Automatic folder-based album creation
- **Advanced Viewer**: Zoom, pan, and gesture controls
- **Media Information**: Display resolution, file size, and duration
- **Permission Management**: Android 13+ media permission support

### Technical Highlights
- **Jetpack Compose UI**: Modern declarative interface
- **MVVM Architecture**: Clean, maintainable code structure
- **Smooth Animations**: 60fps transitions and effects
- **Memory Efficient**: Optimized image loading with Glide
- **Gesture Support**: Pinch-to-zoom, swipe navigation

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or newer
- JDK 17 or higher
- Android SDK API 24+ (Android 7.0)
- Gradle 8.4+

### Installation
1. Clone this repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on device or emulator

```bash
./gradlew assembleDebug
./gradlew installDebug
```

### Permissions Required
- `READ_MEDIA_IMAGES` (Android 13+)
- `READ_MEDIA_VIDEO` (Android 13+)
- `READ_EXTERNAL_STORAGE` (Android 12 and below)

## 🏗️ Architecture

### Project Structure
```
app/src/main/java/com/mediaviewer/ios/
├── MainActivity.kt                 # Entry point
├── data/
│   ├── MediaItem.kt               # Data models
│   └── MediaRepository.kt         # Data layer
└── ui/
    ├── theme/                     # App theming
    ├── components/                # Reusable UI components
    ├── gallery/                   # Gallery screen & ViewModel  
    └── viewer/                    # Media viewer screen
```

### Key Components
- **BlurredGlassCard**: Glass effect container with blur background
- **LiquidGlassEffect**: iOS-style translucent button effects
- **iOSTabRow**: Custom tab navigation matching iOS design
- **MediaViewerScreen**: Full-screen media display with gestures

## 🧪 Testing & CI/CD

### Testing Strategy
- **Unit Tests**: ViewModels and business logic
- **Integration Tests**: Repository and data layer
- **UI Tests**: User interaction flows with Compose Testing

### GitHub Actions Pipeline
- **Code Quality**: Lint analysis and formatting checks
- **Testing**: Automated unit and instrumented tests
- **Security**: OWASP dependency vulnerability scanning  
- **Build**: Debug and release APK generation
- **Release**: Automated GitHub releases with artifacts

### Running Tests
```bash
./gradlew test                          # Unit tests
./gradlew connectedAndroidTest          # Instrumented tests  
./gradlew lint                          # Code analysis
```

## 🎨 Visual Effects Implementation

### Gaussian Blur
- Background blur using advanced compositing
- Multi-layer blur effects for depth
- Performance-optimized blur rendering

### Liquid Glass
- Gradient overlays with transparency
- Border highlights for glass appearance  
- Interactive state changes and animations

### iOS-Style Components
- Authentic iOS navigation patterns
- System color palette adaptation
- Typography matching iOS design guidelines

## 📱 Screenshots

*Screenshots will be available after building and running the app*

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Inspired by iOS 18 Photos app design
- Material Design 3 guidelines
- Jetpack Compose community
- Open source Android development community

---

**Built with ❤️ using modern Android development practices**