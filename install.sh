#!/bin/bash
# ProtonHorizon Installation Guide

# Color codes
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${GREEN}"
echo "╔════════════════════════════════════╗"
echo "║   ProtonHorizon Installation       ║"
echo "╚════════════════════════════════════╝"
echo -e "${NC}"

# Check requirements
check_requirement() {
    if command -v $1 &> /dev/null; then
        echo -e "${GREEN}✓${NC} $1 found"
        return 0
    else
        echo -e "${RED}✗${NC} $1 not found - please install it"
        return 1
    fi
}

echo ""
echo "Checking requirements..."
check_requirement "gradle" || exit 1
check_requirement "cmake" || exit 1
check_requirement "adb" || exit 1

# Ask for Android SDK path
echo ""
echo "Enter Android SDK root path (default: ~/Android/Sdk):"
read -p "> " SDK_PATH
SDK_PATH=${SDK_PATH:-~/Android/Sdk}

if [ ! -d "$SDK_PATH" ]; then
    echo -e "${RED}Error: SDK path not found${NC}"
    exit 1
fi

export ANDROID_SDK_ROOT=$SDK_PATH

# Build
echo ""
echo -e "${YELLOW}Building ProtonHorizon...${NC}"

if [ -f "build.sh" ]; then
    chmod +x build.sh
    ./build.sh
else
    ./gradlew build
fi

if [ $? -ne 0 ]; then
    echo -e "${RED}Build failed!${NC}"
    exit 1
fi

# Installation
echo ""
echo -e "${YELLOW}Installing on device...${NC}"

if adb devices | grep -q "device$"; then
    ./gradlew installDebug
    
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}Installation successful!${NC}"
        echo -e "${YELLOW}Launching app...${NC}"
        adb shell am start -n com.protonhorizon.emulator/.MainActivity
    else
        echo -e "${RED}Installation failed!${NC}"
        exit 1
    fi
else
    echo -e "${YELLOW}No device found. Building APK only...${NC}"
    APK=$(find . -name "*.apk" -type f | head -1)
    if [ -f "$APK" ]; then
        echo -e "${GREEN}APK created: $APK${NC}"
    fi
fi

echo ""
echo -e "${GREEN}Done!${NC}"
