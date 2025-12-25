#!/bin/bash
# Gradle wrapper download script
set -e

GRADLE_VERSION=8.1
GRADLE_SHA=9ba1f23354ce770e8872e0bf6e9ca7e4cacf0a8a97fc0c0e9925d9f90522d521

echo "Downloading Gradle $GRADLE_VERSION..."
curl -L "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" -o gradle.zip

echo "Verifying Gradle checksum..."
echo "$GRADLE_SHA  gradle.zip" | sha256sum -c -

unzip -q gradle.zip
rm gradle.zip

echo "Gradle installation complete!"
