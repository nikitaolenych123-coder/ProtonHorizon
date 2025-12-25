#!/bin/bash
# Quick APK build and install script

set -e

DEVICE=${1:-}
BUILD_TYPE=${2:-debug}

echo "🚀 Quick Build & Install ProtonHorizon"
echo ""

# Build
echo "[1/3] Building $BUILD_TYPE APK..."
./gradlew assemble${BUILD_TYPE^} --quiet

# Find APK
APK=$(find app/build/outputs/apk/$BUILD_TYPE -name "*.apk" | head -1)

if [ -z "$APK" ]; then
    echo "❌ APK not found!"
    exit 1
fi

echo "✅ APK built: $APK"
echo ""

# Check for connected devices
if [ -z "$DEVICE" ]; then
    echo "[2/3] Checking connected devices..."
    DEVICES=$(adb devices | grep -v "List" | grep "device$" | awk '{print $1}')
    
    if [ -z "$DEVICES" ]; then
        echo "⚠️  No devices connected"
        echo "APK ready at: $APK"
        exit 0
    fi
    
    DEVICE=$(echo "$DEVICES" | head -1)
fi

echo "📱 Installing on: $DEVICE"
adb -s "$DEVICE" install -r "$APK"

echo ""
echo "[3/3] Launching app..."
adb -s "$DEVICE" shell am start -n com.protonhorizon.emulator/.MainActivity

echo ""
echo "✅ Done! App is running."
echo ""
echo "View logs:"
echo "  adb logcat | grep ProtonHorizon"
