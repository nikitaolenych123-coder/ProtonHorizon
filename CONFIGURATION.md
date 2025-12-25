# ProtonHorizon Configuration Guide

## Файли Конфігурації

### build.gradle (Top-level)
Налаштування загальної конфігурації проекту, версії плагінів.

### app/build.gradle
Налаштування специфічне для додатку:
- compileSdk: API рівень для компіляції
- targetSdk: API рівень для запуску
- minSdk: Мінімальний API рівень
- ndk.abiFilters: Архітектури для компіляції

### CMakeLists.txt
Конфігурація для побудови C++ компонентів:
- C++ стандарт (C++17)
- Шляхи включення
- Лінкування бібліотек

### gradle.properties
Глобальні властивості для Gradle:
- JVM аргументи
- Parallelization
- AndroidX використання

## Змінні Середовища

```bash
# Android SDK
export ANDROID_SDK_ROOT=/path/to/sdk

# Android NDK
export ANDROID_NDK_ROOT=/path/to/ndk

# Java
export JAVA_HOME=/path/to/java

# Gradle
export GRADLE_USER_HOME=~/.gradle
```

## Кастомізація

### Зміна цільового API

Edit `app/build.gradle`:
```gradle
targetSdk 35  // Змініть тут
```

### Додавання нових архітектур

Edit `app/build.gradle`:
```gradle
ndk {
    abiFilters 'arm64-v8a', 'armeabi-v7a', 'x86_64', 'x86', 'riscv64'
}
```

### Дебагування

Додайте у `build.gradle`:
```gradle
debugOptions {
    waitForDebugger = true
    DebugSymbols = true
}
```

## Продуктивність

### Оптимізація збірки

```gradle
gradle.properties:
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.workers.max=8
```

### Зменшення розміру APK

```gradle
android {
    bundle {
        density {
            enableSplit = true
        }
        abi {
            enableSplit = true
        }
    }
}
```
