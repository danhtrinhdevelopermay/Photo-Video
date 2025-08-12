#!/bin/bash
echo "=== Android Build Test ==="
echo "1. Checking Gradle wrapper..."
./gradlew --version || echo "Gradle wrapper failed"

echo "2. Checking project structure..."
find . -name "*.gradle" -type f

echo "3. Testing clean build..."
./gradlew clean --stacktrace || echo "Clean failed"

echo "4. Testing debug build..."
./gradlew assembleDebug --stacktrace --info

echo "5. Build completed - checking outputs..."
if [ -f "app/build/outputs/apk/debug/app-debug.apk" ]; then
    echo "✅ Success: APK built successfully"
    ls -lh app/build/outputs/apk/debug/app-debug.apk
else
    echo "❌ Failed: No APK found"
    echo "Build directory contents:"
    ls -la app/build/ 2>/dev/null || echo "No build directory"
fi