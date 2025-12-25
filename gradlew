#!/bin/bash
# Gradle wrapper download script
set -e

GRADLE_VERSION=8.1
GRADLE_SHA=a62c5f99585dd9e1f95dab7b9415a0e698fa9dd1e6c38537faa81ac078f4d23e

echo "Downloading Gradle $GRADLE_VERSION..."
curl -L "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" -o gradle.zip

echo "Verifying Gradle checksum..."
echo "$GRADLE_SHA  gradle.zip" | sha256sum -c -

unzip -q -o gradle.zip
rm gradle.zip

echo "Gradle installation complete!"
