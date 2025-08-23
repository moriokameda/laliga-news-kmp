#!/bin/bash

# Generate app icons for Android and iOS from SVG source
# Requires ImageMagick (brew install imagemagick)

SOURCE_SVG="app-icon.svg"
ANDROID_PATH="../../composeApp/src/androidMain/res"
IOS_PATH="../../iosApp/LaLigaNews/Assets.xcassets/AppIcon.appiconset"

# Check if ImageMagick is installed
if ! command -v convert &> /dev/null; then
    echo "ImageMagick is not installed. Please install it first:"
    echo "brew install imagemagick"
    exit 1
fi

echo "Generating app icons from $SOURCE_SVG..."

# Android icons
echo "Generating Android icons..."
mkdir -p "$ANDROID_PATH/mipmap-mdpi"
mkdir -p "$ANDROID_PATH/mipmap-hdpi"
mkdir -p "$ANDROID_PATH/mipmap-xhdpi"
mkdir -p "$ANDROID_PATH/mipmap-xxhdpi"
mkdir -p "$ANDROID_PATH/mipmap-xxxhdpi"

convert "$SOURCE_SVG" -resize 48x48 "$ANDROID_PATH/mipmap-mdpi/ic_launcher.png"
convert "$SOURCE_SVG" -resize 72x72 "$ANDROID_PATH/mipmap-hdpi/ic_launcher.png"
convert "$SOURCE_SVG" -resize 96x96 "$ANDROID_PATH/mipmap-xhdpi/ic_launcher.png"
convert "$SOURCE_SVG" -resize 144x144 "$ANDROID_PATH/mipmap-xxhdpi/ic_launcher.png"
convert "$SOURCE_SVG" -resize 192x192 "$ANDROID_PATH/mipmap-xxxhdpi/ic_launcher.png"

# Android round icons (same as regular for now)
cp "$ANDROID_PATH/mipmap-mdpi/ic_launcher.png" "$ANDROID_PATH/mipmap-mdpi/ic_launcher_round.png"
cp "$ANDROID_PATH/mipmap-hdpi/ic_launcher.png" "$ANDROID_PATH/mipmap-hdpi/ic_launcher_round.png"
cp "$ANDROID_PATH/mipmap-xhdpi/ic_launcher.png" "$ANDROID_PATH/mipmap-xhdpi/ic_launcher_round.png"
cp "$ANDROID_PATH/mipmap-xxhdpi/ic_launcher.png" "$ANDROID_PATH/mipmap-xxhdpi/ic_launcher_round.png"
cp "$ANDROID_PATH/mipmap-xxxhdpi/ic_launcher.png" "$ANDROID_PATH/mipmap-xxxhdpi/ic_launcher_round.png"

# iOS icons
echo "Generating iOS icons..."
mkdir -p "$IOS_PATH"

# iPhone Notification
convert "$SOURCE_SVG" -resize 40x40 "$IOS_PATH/Icon-20@2x.png"
convert "$SOURCE_SVG" -resize 60x60 "$IOS_PATH/Icon-20@3x.png"

# iPhone Settings
convert "$SOURCE_SVG" -resize 58x58 "$IOS_PATH/Icon-29@2x.png"
convert "$SOURCE_SVG" -resize 87x87 "$IOS_PATH/Icon-29@3x.png"

# iPhone Spotlight
convert "$SOURCE_SVG" -resize 80x80 "$IOS_PATH/Icon-40@2x.png"
convert "$SOURCE_SVG" -resize 120x120 "$IOS_PATH/Icon-40@3x.png"

# iPhone App
convert "$SOURCE_SVG" -resize 120x120 "$IOS_PATH/Icon-60@2x.png"
convert "$SOURCE_SVG" -resize 180x180 "$IOS_PATH/Icon-60@3x.png"

# iPad Notifications
convert "$SOURCE_SVG" -resize 20x20 "$IOS_PATH/Icon-20.png"
convert "$SOURCE_SVG" -resize 40x40 "$IOS_PATH/Icon-20@2x-1.png"

# iPad Settings
convert "$SOURCE_SVG" -resize 29x29 "$IOS_PATH/Icon-29.png"
convert "$SOURCE_SVG" -resize 58x58 "$IOS_PATH/Icon-29@2x-1.png"

# iPad Spotlight
convert "$SOURCE_SVG" -resize 40x40 "$IOS_PATH/Icon-40.png"
convert "$SOURCE_SVG" -resize 80x80 "$IOS_PATH/Icon-40@2x-1.png"

# iPad App
convert "$SOURCE_SVG" -resize 76x76 "$IOS_PATH/Icon-76.png"
convert "$SOURCE_SVG" -resize 152x152 "$IOS_PATH/Icon-76@2x.png"

# iPad Pro App
convert "$SOURCE_SVG" -resize 167x167 "$IOS_PATH/Icon-83.5@2x.png"

# App Store
convert "$SOURCE_SVG" -resize 1024x1024 "$IOS_PATH/Icon-1024.png"

echo "Icon generation complete!"
echo "Android icons saved to: $ANDROID_PATH/mipmap-*/"
echo "iOS icons saved to: $IOS_PATH/"