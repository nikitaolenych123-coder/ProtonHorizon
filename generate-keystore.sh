#!/bin/bash
# Script to generate a signing key for ProtonHorizon

set -e

KEYSTORE_FILE="keystore.jks"
KEYSTORE_ALIAS="proton-horizon"
KEYSTORE_PASSWORD="proton-horizon-key"
VALIDITY_DAYS=10000

echo "🔑 Generating ProtonHorizon Signing Key..."
echo ""

if [ -f "$KEYSTORE_FILE" ]; then
    echo "⚠️  Keystore already exists. Remove it first if you want to regenerate:"
    echo "   rm $KEYSTORE_FILE"
    exit 1
fi

keytool -genkey -v \
    -keystore "$KEYSTORE_FILE" \
    -keyalg RSA \
    -keysize 2048 \
    -validity "$VALIDITY_DAYS" \
    -alias "$KEYSTORE_ALIAS" \
    -storepass "$KEYSTORE_PASSWORD" \
    -keypass "$KEYSTORE_PASSWORD" \
    -dname "CN=ProtonHorizon, OU=Development, O=ProtonHorizon Team, L=Ukraine, ST=Ukraine, C=UA"

echo ""
echo "✅ Signing key generated successfully!"
echo ""
echo "📝 Keystore Details:"
echo "   File: $KEYSTORE_FILE"
echo "   Alias: $KEYSTORE_ALIAS"
echo "   Password: $KEYSTORE_PASSWORD"
echo "   Validity: $VALIDITY_DAYS days"
echo ""
echo "⚠️  Store this information securely!"
echo ""
echo "To verify the keystore:"
echo "   keytool -list -v -keystore $KEYSTORE_FILE -storepass $KEYSTORE_PASSWORD"
echo ""
echo "To use with Gradle:"
echo "   export SIGNING_KEY_STORE_PASSWORD=$KEYSTORE_PASSWORD"
echo "   export SIGNING_KEY_ALIAS=$KEYSTORE_ALIAS"
echo "   export SIGNING_KEY_PASSWORD=$KEYSTORE_PASSWORD"
echo "   ./gradlew assembleRelease"
