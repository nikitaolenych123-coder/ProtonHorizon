# ProtonHorizon - Complete Setup Guide

## ✅ Проект Готовий!

ProtonHorizon v1.0.0 повністю налаштований і готовий до розробки та випуску.

## 🚀 Quick Start

### 1. Локальна Збірка

**Debug версія:**
```bash
./quick-build.sh debug
```

**Release версія:**
```bash
./full-build.sh release
```

### 2. Встановлення на Пристрій

**Автоматично:**
```bash
./quick-build.sh
```

**Вручну:**
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## 🔧 GitHub Actions Setup

### 1. Генеруйте Signing Key

```bash
./generate-keystore.sh
```

Це створить `keystore.jks` з паролями:
- Store: `proton-horizon-key`
- Alias: `proton-horizon`
- Key: `proton-horizon-key`

### 2. Додайте до GitHub Secrets

Перейдіть: Settings → Secrets and variables → Actions

```bash
# Кодуйте keystore
base64 -w 0 keystore.jks | pbcopy  # macOS
# або
base64 -w 0 keystore.jks | xclip   # Linux
```

Додайте secrets:
- `SIGNING_KEY` = base64 keystore
- `SIGNING_KEY_STORE_PASSWORD` = `proton-horizon-key`
- `SIGNING_KEY_ALIAS` = `proton-horizon`
- `SIGNING_KEY_PASSWORD` = `proton-horizon-key`

### 3. Запустіть Workflow

Перейдіть: Actions → Build APK → Run workflow

## 📦 Файлова Структура

```
ProtonHorizon/
├── .github/workflows/
│   ├── build.yml              # Auto build на push
│   └── release.yml            # Signed release build
├── app/
│   ├── build.gradle           # App config
│   └── signing.gradle         # Signing config
├── src/                       # Source code
├── *.sh                       # Build scripts
└── GITHUB_ACTIONS_SETUP.md   # CI/CD guide
```

## 📊 Доступні Скрипти

| Скрипт | Використання | Опис |
|--------|-------------|------|
| `quick-build.sh` | `./quick-build.sh` | Швидка збірка і встановлення |
| `full-build.sh` | `./full-build.sh {debug\|release}` | Повна збірка з тестами |
| `build.sh` | `./build.sh` | Базова Gradle збірка |
| `install.sh` | `./install.sh` | Встановлення на пристрій |
| `run.sh` | `./run.sh {start\|logs\|restart}` | Запуск і отримання логів |
| `release.sh` | `./release.sh` | Release APK без підпису |
| `generate-keystore.sh` | `./generate-keystore.sh` | Генерування signing key |

## 🔐 Безпека

⚠️ **ВАЖЛИВО:**
- Ніколи не комітьте `keystore.jks` в Git
- Зберігайте пароли безпечно в GitHub Secrets
- Використовуйте сильні пароли для production
- Регулярно оновлюйте залежності

## 📝 Перед Випуском

### Перевірка коду:
```bash
./gradlew lint
./gradlew test
```

### Побудова для випуску:
```bash
./full-build.sh release
```

### Тестування на реальному пристрої:
```bash
./quick-build.sh debug
# Та перевірити функціональність
```

## 🐛 Вирішення Проблем

### Помилка: "jdk not found"
```bash
export JAVA_HOME=$(which java)
./gradlew -version
```

### Помилка: "android sdk not found"
```bash
export ANDROID_SDK_ROOT=$HOME/Android/Sdk
./gradlew --scan
```

### Помилка: "APK not found"
```bash
./gradlew clean
./full-build.sh debug
```

## 📱 Використання Додатку

1. **Запустіть ProtonHorizon**
   ```bash
   ./quick-build.sh
   ```

2. **Завантажте Компоненти**
   - Натисніть "Компоненти"
   - Виберіть Proton, Wine, DXVK
   - Дочекайтесь завантаження

3. **Запустіть Гру**
   - Натисніть "Вибрати гру"
   - Вкажіть шлях до ISO/EXE
   - Натисніть "Запустити"

4. **Налаштуйте Геймпад**
   - Підключіть контролер
   - Відкройте налаштування
   - Для калібрування

## 📚 Документація

- [README.md](README.md) - Загальна інформація
- [QUICKSTART.md](QUICKSTART.md) - Швидкий старт
- [DEVELOPMENT.md](DEVELOPMENT.md) - Для розробників
- [GITHUB_ACTIONS_SETUP.md](GITHUB_ACTIONS_SETUP.md) - GitHub Actions
- [TROUBLESHOOTING.md](TROUBLESHOOTING.md) - Вирішення проблем

## 🎯 Наступні Кроки

1. ✅ Протестуйте локально
   ```bash
   ./quick-build.sh debug
   ```

2. ✅ Налаштуйте GitHub Actions
   - Див. GITHUB_ACTIONS_SETUP.md

3. ✅ Додайте Secrets в GitHub
   - Див. інструкцію вище

4. ✅ Запустіть Workflow
   - Settings → Actions → Create workflow

5. ✅ Контролюйте Побудови
   - Actions → Build APK → Runs

## ✨ Завершено!

Проект повністю налаштований для:
- ✅ Локальної розробки
- ✅ Автоматичної CI/CD побудови
- ✅ Release management
- ✅ GitHub Pages документація

**Готово до випуску версії 1.0.0! 🚀**

---

For questions or issues, see TROUBLESHOOTING.md
