#!/usr/bin/env python3

import os
import base64
import subprocess

# Base64 encoded minimal PNG for fallback (1x1 pixel red)
FALLBACK_PNG = b'\x89PNG\r\n\x1a\n\x00\x00\x00\rIHDR\x00\x00\x00\x01\x00\x00\x00\x01\x08\x02\x00\x00\x00\x90wS\xde\x00\x00\x00\x0cIDATx\x9cc\xf8\xcf\xc0\x00\x00\x00\x03\x00\x01\x86\x0b\x05\xdd\x00\x00\x00\x00IEND\xaeB`\x82'

def create_placeholder_icons():
    """Create placeholder PNG icons for all required sizes"""
    
    android_path = "../../composeApp/src/androidMain/res"
    ios_path = "../../iosApp/LaLigaNews/Assets.xcassets/AppIcon.appiconset"
    
    print("Creating placeholder app icons...")
    print("Note: These are temporary placeholders. Install ImageMagick or cairosvg to generate proper icons from SVG.")
    
    # Android icon sizes
    android_sizes = {
        "mipmap-mdpi": 48,
        "mipmap-hdpi": 72,
        "mipmap-xhdpi": 96,
        "mipmap-xxhdpi": 144,
        "mipmap-xxxhdpi": 192
    }
    
    # Create Android icons
    print("\nCreating Android placeholder icons...")
    for folder, size in android_sizes.items():
        folder_path = os.path.join(android_path, folder)
        os.makedirs(folder_path, exist_ok=True)
        
        # Create placeholder files
        icon_path = os.path.join(folder_path, "ic_launcher.png")
        with open(icon_path, 'wb') as f:
            f.write(FALLBACK_PNG)
        
        round_icon_path = os.path.join(folder_path, "ic_launcher_round.png")
        with open(round_icon_path, 'wb') as f:
            f.write(FALLBACK_PNG)
        
        print(f"  Created {folder}/ic_launcher.png (placeholder)")
    
    # iOS icon sizes
    ios_sizes = [
        "Icon-20.png", "Icon-20@2x.png", "Icon-20@3x.png",
        "Icon-29.png", "Icon-29@2x.png", "Icon-29@3x.png",
        "Icon-40.png", "Icon-40@2x.png", "Icon-40@3x.png",
        "Icon-60@2x.png", "Icon-60@3x.png",
        "Icon-76.png", "Icon-76@2x.png",
        "Icon-83.5@2x.png", "Icon-1024.png"
    ]
    
    # Create iOS icons
    print("\nCreating iOS placeholder icons...")
    os.makedirs(ios_path, exist_ok=True)
    
    for filename in ios_sizes:
        icon_path = os.path.join(ios_path, filename)
        with open(icon_path, 'wb') as f:
            f.write(FALLBACK_PNG)
        print(f"  Created {filename} (placeholder)")
    
    print("\n✅ Placeholder icons created successfully!")
    print("\n⚠️  IMPORTANT: These are temporary 1x1 pixel placeholders.")
    print("To generate proper icons from the SVG, please install ImageMagick:")
    print("  brew install imagemagick")
    print("Then run: ./generate-icons.sh")
    
    return True

def try_svg_conversion():
    """Try to convert SVG using macOS native tools"""
    try:
        # Check if we can use sips (macOS built-in)
        result = subprocess.run(['which', 'sips'], capture_output=True, text=True)
        if result.returncode == 0:
            print("\n💡 TIP: You can also use Quick Look Generator to convert SVG to PNG on macOS")
            print("Or install librsvg: brew install librsvg")
    except:
        pass

if __name__ == "__main__":
    create_placeholder_icons()
    try_svg_conversion()