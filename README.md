# ProtonHorizon
## Android Emulator з підтримкою Proton, DXVK, VK3D і Winlator

ProtonHorizon - це розширена версія Horizon Emulator, яка об'єднує найкращі функції для емуляції Windows ігор та програм на Android.

### Основні Особливості

#### 1. **Підтримка Геймпадів**
- Xbox контролери
- PlayStation контролери  
- Nintendo Joy-Con
- Steam контролери
- Універсальні Bluetooth геймпади

#### 2. **Графічні API**
- **Vulkan** - основний API для найкращої продуктивності
- **DXVK** - трансляція Direct3D 9-12 на Vulkan
- **VK3D** (VKD3D-Proton) - трансляція Direct3D 12 на Vulkan
- **OpenGL** - підтримка для обраних ігор

#### 3. **Завантаження Компонентів**
- Proton (включаючи Proton-GE)
- Wine (чистий Wine та Wine-Staging)
- DXVK (для Direct3D 9-11)
- VK3D-Proton (для Direct3D 12)
- Vulkan Layers

#### 4. **Оптимізація Вінлаторської Версії**
- Інтеграція Winlator бази для ARM64
- Оптимізовані системні бібліотеки
- Швидший запуск і виконання
- Зменшені вимоги до RAM

### Архітектура Проекту

```
ProtonHorizon/
├── src/
│   ├── android/          # Kotlin код для Android
│   ├── core/             # Основне ядро емулятора
│   ├── gamepad/          # Обробка геймпада
│   ├── gpu/              # Контекст графіки
│   └── proton/           # Двигун Proton
├── app/                  # Android приложение
├── jni/                  # Native Interface
└── build.gradle          # Build configuration
```

### Вимоги

- Android 7.0+ (API 24+)
- Мінімум 4GB RAM (рекомендується 6GB+)
- Вільне місце на диску: 5GB+ для компонентів
- Тип процесора: ARM64 (рекомендується), ARM32, x86, x86_64

### Встановлення

```bash
git clone https://github.com/your-repo/ProtonHorizon.git
cd ProtonHorizon
./gradlew build
./gradlew installDebug
```

### Автоматична Збірка (GitHub Actions)

Проект налаштований для автоматичної збірки APK за допомогою GitHub Actions.

**Налаштування:**
1. Див. [GITHUB_ACTIONS_SETUP.md](GITHUB_ACTIONS_SETUP.md)
2. Додайте secrets в GitHub Settings
3. APK буде автоматично будуватися при push

**Запуск workflow:**
- Автоматично при push на `main` або `develop`
- Вручну через Actions → Run workflow

### Основні Компоненти

**GamepadManager** - управління контролерами і входом
**ComponentManager** - управління Proton, DXVK, VK3D компонентами
**DownloadManager** - завантаження компонентів з інтернету
**EmulatorEngine** - основний двигун емулятора
**ProtonEngine** - обробка Wine/Proton середовища

### Ліцензія

MIT License

### Подяка

- Horizon Emulator Team
- GloriousEggroll (Proton-GE)
- Doitsujin (DXVK)
- HansKristian (VKD3D-Proton)
- Succubussix (Winlator) 
