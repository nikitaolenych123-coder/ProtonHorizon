# GitHub Actions Setup для ProtonHorizon

## Налаштування автоматичної збірки APK

### 1. Генерування Signing Key

#### Локально:

```bash
cd /workspaces/ProtonHorizon
chmod +x generate-keystore.sh
./generate-keystore.sh
```

Це створить `keystore.jks` з таким паролем:
- **Store Password**: proton-horizon-key
- **Key Alias**: proton-horizon
- **Key Password**: proton-horizon-key

#### Збереження Keystore для GitHub:

```bash
# Кодування в Base64
base64 -w 0 keystore.jks > keystore.jks.b64

# Скопіюйте вміст файлу
cat keystore.jks.b64
```

### 2. Налаштування GitHub Secrets

Перейдіть на: `Settings → Secrets and variables → Actions`

Додайте наступні secrets:

| Secret Name | Value |
|------------|-------|
| `SIGNING_KEY` | Вміст keystore.jks.b64 |
| `SIGNING_KEY_STORE_PASSWORD` | proton-horizon-key |
| `SIGNING_KEY_ALIAS` | proton-horizon |
| `SIGNING_KEY_PASSWORD` | proton-horizon-key |

### 3. Автоматична Збірка

#### Trigger Events:

**build.yml** запускається на:
- Push на `main` або `develop`
- Pull Request на `main` або `develop`
- Manual trigger (workflow_dispatch)

**release.yml** запускається на:
- Manual trigger з вибором типу релізу

### 4. Результати Збірки

APK файли будуть доступні у "Artifacts":
- `debug-apk` - Debug версія
- `release-apk` - Release версія без підпису
- `signed-release-aab` - Signed AAB для Google Play

### 5. Видання Релізу

Для автоматичного видання:

```bash
git tag v1.1.0
git push origin v1.1.0
```

Це запустить release job, що створить GitHub Release з APK файлами.

## Локальна Збірка з Підписанням

### Release APK:

```bash
# Встановіть змінні середовища
export SIGNING_KEY_STORE_PASSWORD="proton-horizon-key"
export SIGNING_KEY_ALIAS="proton-horizon"
export SIGNING_KEY_PASSWORD="proton-horizon-key"
export SIGNING_KEY_PATH="keystore.jks"

# Збирайте
./gradlew assembleRelease

# APK буде у: app/build/outputs/apk/release/
```

### Debug APK:

```bash
./gradlew assembleDebug

# APK буде у: app/build/outputs/apk/debug/
```

## Перевірка Workflow

1. Перейдіть на вкладку "Actions" у GitHub
2. Виберіть workflow "Build APK"
3. Натисніть "Run workflow"
4. Виберіть гілку
5. Натисніть "Run workflow"

## Вирішення Проблем

### Помилка: "Secret not found"
- Перевірте назви secrets в Settings
- Назви чутливі до регістру

### Помилка: "APK not found"
- Перевірте наявність JDK 17
- Перевірте версію Android SDK API 34
- Дивіться логи в workflow runs

### Помилка: "Keystore invalid"
- Перегенеруйте keystore.jks
- Перевірте Base64 кодування
- Переконайтесь що пароль правильний

## Інші Варіанти

### Кастомний signing.gradle

Може бути змінено файл `app/signing.gradle` для інших варіантів конфігурації.

### CI/CD Pipeline

Додайте інші workflow для:
- Тестування (unit tests)
- Статичного аналізу коду
- Публікації в Google Play Store

## Безпека

⚠️ **Важливо:**
- Ніколи не комітьте `keystore.jks` у Git
- Зберігайте пароли безпечно
- Використовуйте GitHub Secrets
- Регулярно оновлюйте залежності
