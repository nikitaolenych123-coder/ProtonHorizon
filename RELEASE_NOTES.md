 .помилка ну пофікс подивись може де# ProtonHorizon - Release Notes v1.0.0

## Overview
ProtonHorizon is a modern Android emulator application built on Horizon Emulator with extensive support for Proton, DXVK, VK3D, Wine, and advanced gamepad integration.

## Features

### Core Emulation
- **Horizon Emulator Base**: Built on proven Horizon Emulator architecture
- **Multi-Gamepad Support**: Full gamepad/joystick integration (Xbox, PlayStation, custom controllers)
- **Component Download Manager**: Download and manage emulation components (Proton, DXVK, VK3D, Wine)

### Graphics & Performance
- **Vulkan Support**: Direct Vulkan API support for modern graphics
- **DXVK Integration**: DirectX 9-12 through Vulkan translation layer
- **VK3D Support**: Additional graphics acceleration options
- **Wine/Proton Integration**: Full Windows compatibility layer support

### Advanced Features
- **Winlator Optimizations**: Bionic/glibc environment compatibility
- **Performance Optimization**: Memory management, JNI optimization, background cleanup
- **Modular Architecture**: Separate components for gamepad, GPU, core emulation, and Proton engine
- **Preferences Management**: Persistent configuration and logging

## Build Information

### Requirements
- Android SDK API 34+
- NDK 25.x
- Java 17+
- Gradle 8.1

### Supported Architectures
- ARM64 v8a (primary)
- ARMv7a
- x86_64
- x86

### Build Configuration
The project uses Gradle with CMake for native compilation:
- `app/build.gradle`: Android app configuration
- `CMakeLists.txt`: Native C++ build configuration
- `.github/workflows/android-build.yml`: CI/CD automation

## Installation & Usage

### From GitHub Release
1. Download the latest APK from [GitHub Releases](https://github.com/nikitaolenych123-coder/ProtonHorizon/releases)
2. Enable "Unknown Sources" in Android Settings
3. Install the APK
4. Launch ProtonHorizon

### From Source
```bash
git clone https://github.com/nikitaolenych123-coder/ProtonHorizon.git
cd ProtonHorizon
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
./gradlew assembleDebug
# Install: adb install app/build/outputs/apk/debug/*.apk
```

## Development

### Project Structure
```
ProtonHorizon/
├── app/                          # Android application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java/
│   │   │   │   └── com/protonhorizon/emulator/
│   │   │   │       ├── MainActivity.kt
│   │   │   │       ├── EmulatorEngine.kt
│   │   │   │       ├── GamepadManager.kt
│   │   │   │       ├── ComponentManager.kt
│   │   │   │       └── ...
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       ├── values/
│   │   │       └── mipmap/
│   ├── build.gradle
│   └── proguard-rules.pro
│
├── src/                          # Native C++ code
│   ├── android/                  # JNI bridge & Kotlin code
│   ├── core/                     # Core emulator
│   ├── gamepad/                  # Gamepad handling
│   ├── gpu/                      # GPU context management
│   └── proton/                   # Proton/Wine engine
│
├── .github/workflows/
│   └── android-build.yml         # GitHub Actions CI
│
├── CMakeLists.txt                # Top-level CMake config
├── build.gradle                  # Root Gradle config
├── settings.gradle.kts           # Gradle settings
└── gradlew                        # Gradle wrapper script
```

### Building
```bash
# Debug build
./gradlew assembleDebug

# Release build (requires keystore)
./gradlew assembleRelease -Pkeystore.path=./keystore.jks

# With custom environment variables
export SIGNING_KEY_PATH=./keystore.jks
export SIGNING_KEY_STORE_PASSWORD=password
export SIGNING_KEY_ALIAS=android
export SIGNING_KEY_PASSWORD=password
./gradlew assembleRelease
```

## CI/CD

### GitHub Actions Workflow
The project includes an automated CI/CD workflow (`.github/workflows/android-build.yml`) that:
1. Checks out code on push to main
2. Sets up JDK 17 and Android SDK/NDK
3. Builds debug APK
4. Downloads apktool for inclusion in releases
5. Uploads artifacts to GitHub Actions
6. Creates GitHub Release with APK and apktool.jar

### Signing
For signed releases, provide these repository secrets:
- `SIGNING_KEY_BASE64`: Base64-encoded keystore file
- `KEYSTORE_PASSWORD`: Keystore password
- `KEY_ALIAS`: Key alias in keystore
- `KEY_PASSWORD`: Key password

To generate the secret:
```bash
base64 -w 0 < keystore.jks | xclip -i
# Paste into GitHub Actions Secrets
```

## API & Component Reference

### MainActivity
- Entry point for the application
- Initializes UI and EmulatorEngine
- Provides buttons for starting/stopping emulation

### EmulatorEngine
- JNI interface to C++ native layer
- Methods: `startEmulator()`, `stopEmulator()`, `pause()`, `resume()`
- Manages emulator lifecycle

### GamepadManager
- Detects and manages gamepads
- Maps input events to emulator
- Supports multiple simultaneous gamepads

### ComponentManager
- Downloads and installs components (Proton, DXVK, VK3D, Wine)
- Manages component versioning and updates
- Handles installation paths and verification

### WinlatorOptimizer
- Bionic/glibc compatibility management
- Environment variable setup for Wine compatibility
- Performance tuning options

### Native Layer (C++)
- `emulator.cpp/.h`: Core emulation loop
- `gamepad.cpp/.h`: Low-level gamepad state machine
- `gpu_context.cpp/.h`: GPU context management (Vulkan, DXVK, VK3D)
- `proton_engine.cpp/.h`: Proton/Wine integration layer
- `jni_bridge.cpp`: JNI function implementations

## Documentation

See `/workspaces/ProtonHorizon/` for complete documentation:
- [README.md](./README.md) - Project overview
- [DEVELOPMENT.md](./DEVELOPMENT.md) - Development guide
- [CONFIGURATION.md](./CONFIGURATION.md) - Configuration options
- [TROUBLESHOOTING.md](./TROUBLESHOOTING.md) - Troubleshooting guide
- [CI.md](./CI.md) - CI/CD setup

## Contributing
Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit changes (`git commit -am 'Add YourFeature'`)
4. Push to branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

## License
Licensed under the MIT License - see [LICENSE](./LICENSE) file for details.

## Acknowledgments
- Based on Horizon Emulator architecture
- Uses Proton, DXVK, VK3D, and Wine technologies
- Gamepad integration inspired by Ludachi
- Built with Gradle, CMake, and Android NDK

## Release Artifacts
This release includes:
- `app-debug.apk`: Debug build for testing
- `apktool.jar`: APK decompilation and modification tool (v2.7.0)
- `apktool`: Shell script wrapper for apktool.jar

## Status
**v1.0.0 Preview**: Initial release with core features implemented. Full CI/CD pipeline ready for automated builds.

---

For latest updates and issues, visit: https://github.com/nikitaolenych123-coder/ProtonHorizon
