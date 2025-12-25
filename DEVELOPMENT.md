# ProtonHorizon - Developer Guide

## Архітектура

ProtonHorizon складається з кількох основних компонентів:

### 1. Android Layer (Kotlin)
- **MainActivity** - основна активність
- **EmulatorEngine** - центральний интерфейс до емулятора
- **GamepadManager** - управління контролерами
- **ComponentManager** - управління компонентами
- **DownloadManager** - завантаження компонентів
- **WinlatorOptimizer** - оптимізація для мобільної платформи

### 2. Native Layer (C++)
- **Emulator Core** - основна логіка емуляції
- **GPU Context** - управління графічними API
- **GamepadInput** - обробка вводу геймпада
- **ProtonEngine** - управління Wine/Proton середовищем
- **JNI Bridge** - мост між Java і C++

## Побудова Проекту

### Вимоги
- Android SDK API 34+
- Android NDK r25+
- CMake 3.22+
- Gradle 8.1+

### Кроки побудови

```bash
# 1. Клонування репозиторію
git clone https://github.com/your-repo/ProtonHorizon.git
cd ProtonHorizon

# 2. Налаштування Android SDK (опціонально)
export ANDROID_SDK_ROOT=/path/to/android-sdk
export ANDROID_NDK_ROOT=/path/to/android-ndk

# 3. Побудова проекту
./gradlew build

# 4. Створення debug APK
./gradlew assembleDebug

# 5. Встановлення на пристрій
./gradlew installDebug

# 6. Запуск
adb shell am start -n com.protonhorizon.emulator/.MainActivity
```

## Додавання нового графічного API

1. Створіть клас в `src/gpu/`:

```cpp
class MyGraphicsAPI : public GPUContext {
public:
    bool Initialize(GraphicsAPI api) override {
        // Ваша реалізація
    }
    
    void Shutdown() override { }
    bool Present() override { }
    void ClearScreen(uint32_t color) override { }
};
```

2. Реєструйте його в `EmulatorEngine`:

```kotlin
fun setGraphicsAPI(api: String): Boolean {
    return when(api.lowercase()) {
        "my-api" -> setGraphicsAPI(api)
        else -> false
    }
}
```

## Додавання підтримки геймпада

1. Розширте `GamepadInput`:

```cpp
enum class ControllerType {
    NEW_CONTROLLER = 0x20
};
```

2. Обробіть входу в `GamepadManager`:

```kotlin
fun onNewControllerInput(deviceId: Int, data: ByteArray) {
    // Ваша обробка
}
```

## Структура Файлів

```
src/
├── android/                    # Kotlin/Java код
│   ├── MainActivity.kt
│   ├── EmulatorEngine.kt
│   ├── GamepadManager.kt
│   ├── ComponentManager.kt
│   ├── DownloadManager.kt
│   ├── WinlatorOptimizer.kt
│   ├── WineContext.kt
│   └── OptimizedComponentManager.kt
├── core/                       # Основне ядро
│   ├── emulator.h
│   └── emulator.cpp
├── gamepad/                    # Управління геймпадом
│   ├── gamepad.h
│   └── gamepad.cpp
├── gpu/                        # Графіка
│   ├── gpu_context.h
│   └── gpu_context.cpp
└── proton/                     # Wine/Proton
    ├── proton_engine.h
    └── proton_engine.cpp
```

## Debugging

### Перегляд логів
```bash
adb logcat | grep ProtonHorizon
```

### Debug режим
```bash
./gradlew installDebug
adb shell am start -D -n com.protonhorizon.emulator/.MainActivity
```

## Оптимізація

### Вимоги до продуктивності
- Мінімум 60 FPS
- Використання менше 2GB RAM (на мобільних)
- CPU використання < 80%

### Рекомендації
1. Використовуйте Vulkan для найкращої продуктивності
2. Включіть VSync для зменшення використання батареї
3. Зменшіть роздільну здатність при необхідності

## Тестування

### Юніт тести
```bash
./gradlew test
```

### Інструментовані тести
```bash
./gradlew connectedAndroidTest
```

## Фіксування помилок

### Звичайні проблеми

**Помилка: "libc.so.6 not found"**
- Переконайтесь, що Winlator базові бібліотеки встановлені
- Перевірте PATH до бібліотек

**Помилка: "Vulkan not supported"**
- Перевірте наявність Vulkan драйверів на пристрої
- Спробуйте DXVK або OpenGL

**Низька продуктивність**
- Зменшіть роздільну здатність
- Відключіть вертикальну синхронізацію
- Використовуйте 32-бітну версію гри

## Контрибютинг

1. Fork репозиторію
2. Створіть гілку фіч (`git checkout -b feature/NewFeature`)
3. Зафіксуйте зміни (`git commit -am 'Add NewFeature'`)
4. Push на гілку (`git push origin feature/NewFeature`)
5. Створіть Pull Request

## Ліцензія

MIT License
