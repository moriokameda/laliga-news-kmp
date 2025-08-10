#!/bin/bash

# Script to generate release keystore for La Liga News app
# This should be run once and the keystore should be kept secure

KEYSTORE_DIR="keystore"
KEYSTORE_FILE="$KEYSTORE_DIR/laliga-news-release.keystore"
KEY_ALIAS="laliga-news"

# Create keystore directory if it doesn't exist
mkdir -p $KEYSTORE_DIR

# Check if keystore already exists
if [ -f "$KEYSTORE_FILE" ]; then
    echo "Keystore already exists at $KEYSTORE_FILE"
    echo "To regenerate, please delete the existing keystore first."
    exit 1
fi

echo "Generating release keystore for La Liga News app..."
echo "Please provide the following information:"

# Generate keystore
keytool -genkey -v \
    -keystore "$KEYSTORE_FILE" \
    -alias "$KEY_ALIAS" \
    -keyalg RSA \
    -keysize 2048 \
    -validity 10000 \
    -dname "CN=La Liga News, OU=Mobile Development, O=La Liga News App, L=Tokyo, ST=Tokyo, C=JP"

if [ $? -eq 0 ]; then
    echo ""
    echo "Keystore generated successfully!"
    echo "Location: $KEYSTORE_FILE"
    echo "Alias: $KEY_ALIAS"
    echo ""
    echo "IMPORTANT: Keep this keystore file secure!"
    echo "You will need it for all future app updates."
    echo ""
    echo "To use this keystore in your builds, set these environment variables:"
    echo "  export KEYSTORE_FILE=$KEYSTORE_FILE"
    echo "  export KEYSTORE_PASSWORD=<your_keystore_password>"
    echo "  export KEY_ALIAS=$KEY_ALIAS"
    echo "  export KEY_PASSWORD=<your_key_password>"
else
    echo "Failed to generate keystore"
    exit 1
fi