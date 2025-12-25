#!/bin/bash
# Скрипт для запуску вузлів та перевірки логів

set -e

PACKAGE_NAME="com.protonhorizon.emulator"
ACTIVITY_NAME="$PACKAGE_NAME.MainActivity"

# Функції
start_app() {
    echo "Запуск $PACKAGE_NAME..."
    adb shell am start -W -S $ACTIVITY_NAME
}

stop_app() {
    echo "Зупинення $PACKAGE_NAME..."
    adb shell am force-stop $PACKAGE_NAME
}

show_logs() {
    echo "Показ логів..."
    adb logcat | grep "ProtonHorizon"
}

run_tests() {
    echo "Запуск тестів..."
    ./gradlew connectedAndroidTest
}

# Обробка аргументів
case "${1:-start}" in
    start)
        start_app
        sleep 2
        show_logs
        ;;
    stop)
        stop_app
        ;;
    restart)
        stop_app
        sleep 1
        start_app
        sleep 2
        show_logs
        ;;
    logs)
        show_logs
        ;;
    test)
        run_tests
        ;;
    *)
        echo "Використання: $0 {start|stop|restart|logs|test}"
        exit 1
        ;;
esac
