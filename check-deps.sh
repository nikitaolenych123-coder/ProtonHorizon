#!/bin/bash
# Скрипт для встановлення залежностей

set -e

echo "Installing ProtonHorizon dependencies..."

# Перевірка наявності необхідних інструментів
check_command() {
    if ! command -v $1 &> /dev/null; then
        echo "Error: $1 is required but not installed."
        return 1
    fi
}

# Залежності
REQUIRED_COMMANDS=("gradle" "cmake" "adb")

echo "Checking required commands..."
for cmd in "${REQUIRED_COMMANDS[@]}"; do
    if ! check_command "$cmd"; then
        echo "Please install $cmd"
        case $cmd in
            gradle)
                echo "Download from: https://gradle.org/releases/"
                ;;
            cmake)
                echo "Download from: https://cmake.org/download/"
                ;;
            adb)
                echo "Install Android SDK Platform Tools"
                ;;
        esac
        exit 1
    fi
done

echo ""
echo "All dependencies are installed."
echo "You can now build ProtonHorizon with: ./gradlew build"
