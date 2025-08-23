#!/usr/bin/env python3

import os
import subprocess
import sys

def check_dependencies():
    """Check if required dependencies are installed"""
    try:
        import cairosvg
        import PIL
        return True
    except ImportError:
        print("Installing required dependencies...")
        subprocess.run([sys.executable, "-m", "pip", "install", "cairosvg", "Pillow"], check=True)
        return True

def generate_icons():
    """Generate app icons from SVG source"""
    import cairosvg
    from PIL import Image
    import io
    
    source_svg = "app-icon.svg"
    android_path = "../../composeApp/src/androidMain/res"
    ios_path = "../../iosApp/LaLigaNews/Assets.xcassets/AppIcon.appiconset"
    
    # Read SVG file
    with open(source_svg, 'rb') as f:
        svg_data = f.read()
    
    print(f"Generating app icons from {source_svg}...")
    
    # Android icon sizes
    android_sizes = {
        "mipmap-mdpi": 48,
        "mipmap-hdpi": 72,
        "mipmap-xhdpi": 96,
        "mipmap-xxhdpi": 144,
        "mipmap-xxxhdpi": 192
    }
    
    # Generate Android icons
    print("Generating Android icons...")
    for folder, size in android_sizes.items():
        folder_path = os.path.join(android_path, folder)
        os.makedirs(folder_path, exist_ok=True)
        
        # Convert SVG to PNG
        png_data = cairosvg.svg2png(bytestring=svg_data, output_width=size, output_height=size)
        
        # Save regular icon
        icon_path = os.path.join(folder_path, "ic_launcher.png")
        with open(icon_path, 'wb') as f:
            f.write(png_data)
        
        # Save round icon (same for now)
        round_icon_path = os.path.join(folder_path, "ic_launcher_round.png")
        with open(round_icon_path, 'wb') as f:
            f.write(png_data)
        
        print(f"  Created {folder}/ic_launcher.png ({size}x{size})")
    
    # iOS icon sizes (name: size)
    ios_sizes = {
        "Icon-20.png": 20,
        "Icon-20@2x.png": 40,
        "Icon-20@3x.png": 60,
        "Icon-29.png": 29,
        "Icon-29@2x.png": 58,
        "Icon-29@3x.png": 87,
        "Icon-40.png": 40,
        "Icon-40@2x.png": 80,
        "Icon-40@3x.png": 120,
        "Icon-60@2x.png": 120,
        "Icon-60@3x.png": 180,
        "Icon-76.png": 76,
        "Icon-76@2x.png": 152,
        "Icon-83.5@2x.png": 167,
        "Icon-1024.png": 1024
    }
    
    # Generate iOS icons
    print("Generating iOS icons...")
    os.makedirs(ios_path, exist_ok=True)
    
    for filename, size in ios_sizes.items():
        # Convert SVG to PNG
        png_data = cairosvg.svg2png(bytestring=svg_data, output_width=size, output_height=size)
        
        # Save icon
        icon_path = os.path.join(ios_path, filename)
        with open(icon_path, 'wb') as f:
            f.write(png_data)
        
        print(f"  Created {filename} ({size}x{size})")
    
    print("\nIcon generation complete!")
    print(f"Android icons saved to: {android_path}/mipmap-*/")
    print(f"iOS icons saved to: {ios_path}/")

if __name__ == "__main__":
    if check_dependencies():
        generate_icons()