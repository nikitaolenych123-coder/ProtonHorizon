# ProtonHorizon Quick Start

## Швидкий Старт

### 1. Встановлення на пристрій

```bash
./gradlew installDebug
./gradlew installRelease
```

### 2. Запуск від логування

```bash
./run.sh start
./run.sh logs
./run.sh restart
```

### 3. Встановлення компонентів

1. Запустіть додаток
2. Перейдіть у "Компоненти"
3. Натисніть "Завантажити Proton"
4. Виберіть версію
5. Натисніть "Встановити"

### 4. Запуск гри

1. Натисніть "Вибрати гру"
2. Вкажіть шлях до ISO/EXE файлу
3. Натисніть "Запустити"

## Рекомендовані Налаштування

### Для Нових Користувачів
- Graphics API: **Vulkan**
- Resolution: **1280x720**
- VSync: **Вкл**
- Texture Quality: **High**

### Для Старих Пристроїв
- Graphics API: **OpenGL**
- Resolution: **640x480**
- VSync: **Викл**
- Texture Quality: **Low**

### Для Гіх ПК
- Graphics API: **DXVK/VK3D**
- Resolution: **1920x1080+**
- VSync: **Викл**
- Texture Quality: **Ultra**

## Вирішення Проблем

Див. [TROUBLESHOOTING.md](TROUBLESHOOTING.md)

## Документація

- [README.md](README.md) - Загальна інформація
- [DEVELOPMENT.md](DEVELOPMENT.md) - Для розробників
- [CONFIGURATION.md](CONFIGURATION.md) - Налаштування
- [CHANGELOG.md](CHANGELOG.md) - Історія змін
- [CONTRIBUTING.md](CONTRIBUTING.md) - Як сприяти
