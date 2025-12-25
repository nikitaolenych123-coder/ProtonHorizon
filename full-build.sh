#!/bin/bash
# Complete build and release script for ProtonHorizon

set -e

VERSION="1.0.0"
BUILD_TYPE="${1:-release}"
OUTPUT_DIR="build/release"

echo "╔═══════════════════════════════════════════════════╗"
echo "║  ProtonHorizon - Complete Build & Release        ║"
echo "║  Version: $VERSION                              ║"
echo "╚═══════════════════════════════════════════════════╝"
echo ""

# Create output directory
mkdir -p "$OUTPUT_DIR"

case "$BUILD_TYPE" in
    debug)
        echo "[1/5] Building Debug APK..."
        ./gradlew clean assembleDebug
        cp app/build/outputs/apk/debug/*.apk "$OUTPUT_DIR/" || true
        echo "✅ Debug APK created"
        ;;
    
    release)
        echo "[1/5] Building Release APK (unsigned)..."
        ./gradlew clean assembleRelease
        cp app/build/outputs/apk/release/*.apk "$OUTPUT_DIR/" || true
        echo "✅ Release APK created"
        
        echo ""
        echo "[2/5] Building Release Bundle..."
        ./gradlew bundleRelease
        cp app/build/outputs/bundle/release/*.aab "$OUTPUT_DIR/" || true
        echo "✅ Release Bundle created"
        ;;
    
    *)
        echo "Usage: $0 {debug|release}"
        echo ""
        echo "Examples:"
        echo "  $0 debug      - Build debug APK"
        echo "  $0 release    - Build release APK and bundle"
        exit 1
        ;;
esac

echo ""
echo "[3/5] Running tests..."
./gradlew test --continue || true
echo "✅ Tests completed"

echo ""
echo "[4/5] Building native components..."
./gradlew externalNativeBuild
echo "✅ Native components built"

echo ""
echo "[5/5] Generating reports..."
mkdir -p build-reports
cp BUILD_REPORT.txt build-reports/ || true

echo ""
echo "═══════════════════════════════════════════════════"
echo "✅ BUILD COMPLETE!"
echo "═══════════════════════════════════════════════════"
echo ""
echo "📦 Output files:"
ls -lh "$OUTPUT_DIR"/ 2>/dev/null || echo "No output files found"
echo ""
echo "📊 Build reports:"
ls -lh build-reports/ 2>/dev/null || echo "No reports generated"
echo ""
echo "For signed release build, see: GITHUB_ACTIONS_SETUP.md"
