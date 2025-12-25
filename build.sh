#!/bin/bash
# Скрипт для побудови ProtonHorizon

set -e

echo "==================================="
echo "ProtonHorizon Build Script"
echo "==================================="

# Перевірка необхідних інструментів
check_tool() {
    if ! command -v $1 &> /dev/null; then
        echo "Error: $1 not found. Please install it."
        exit 1
    fi
}

check_tool "gradle"
check_tool "cmake"

# Переменные
BUILD_TYPE=${1:-debug}
OUTPUT_DIR="build/outputs"

echo ""
echo "Building ProtonHorizon ($BUILD_TYPE)..."
echo ""

# Очистка попередніх збірок
echo "[1/4] Cleaning previous builds..."
./gradlew clean

# Побудова C++ компонентів
echo "[2/4] Building native components..."
./gradlew externalNativeBuildDebug

# Побудова APK
echo "[3/4] Building APK..."
./gradlew assemble${BUILD_TYPE^}

# Розміщення артефактів
echo "[4/4] Organizing build artifacts..."
mkdir -p $OUTPUT_DIR
find build -name "*.apk" -exec cp {} $OUTPUT_DIR/ \;

echo ""
echo "==================================="
echo "Build Complete!"
echo "==================================="
echo "APK location: $OUTPUT_DIR"
ls -lh $OUTPUT_DIR/*.apk 2>/dev/null || echo "No APK found"
