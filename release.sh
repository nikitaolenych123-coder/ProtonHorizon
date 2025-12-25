#!/bin/bash
# Скрипт для виробничої збірки APK

set -e

BUILD_TYPE="release"
SIGN_KEY=""

# Парсинг аргументів
while [[ $# -gt 0 ]]; do
    case $1 in
        -k|--keystore)
            SIGN_KEY="$2"
            shift 2
            ;;
        *)
            shift
            ;;
    esac
done

echo "Building ProtonHorizon Release APK..."

# Очистка
./gradlew clean

# Побудова
./gradlew assembleRelease

# Якщо надано ключ, підписуємо APK
if [ -n "$SIGN_KEY" ] && [ -f "$SIGN_KEY" ]; then
    echo "Signing APK with keystore..."
    # Це потребує додаткової конфігурації
fi

# Пошук створеного APK
APK=$(find build/outputs/apk/release -name "*.apk" | head -1)

if [ -f "$APK" ]; then
    echo "Release APK created: $APK"
    ls -lh "$APK"
    
    # Опціонально: завантажити розміри
    SIZE=$(stat -f%z "$APK" 2>/dev/null || stat -c%s "$APK")
    echo "APK Size: $((SIZE / 1024 / 1024))MB"
else
    echo "Error: APK not found!"
    exit 1
fi
