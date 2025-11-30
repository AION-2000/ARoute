# 🚀 ARoute

<div align="center">

![Platform](https://img.shields.io/badge/platform-Android-brightgreen.svg?style=for-the-badge&logo=android)
![Language](https://img.shields.io/badge/Kotlin-1.9+-7F52FF.svg?style=for-the-badge&logo=kotlin)
![ARCore](https://img.shields.io/badge/ARCore-Supported-4285F4.svg?style=for-the-badge&logo=google)
![Sceneform](https://img.shields.io/badge/Sceneform-v1.21.0-FF6B6B.svg?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-v1.0_Complete-00C853.svg?style=for-the-badge)

### **Your Gateway to Augmented Reality Development**

*A production-ready AR application showcasing modern Android development with ARCore and Sceneform*

[Features](#-features) • [Demo](#-demo) • [Installation](#-installation) • [Usage](#-usage) • [Architecture](#-architecture) • [Contributing](#-contributing)

---

</div>

## 📱 Overview

**ARoute** is a professionally crafted Android application that demonstrates the full potential of Augmented Reality on mobile devices. Built with modern Android development practices, it serves as both a functional AR experience and a learning resource for developers exploring ARCore integration.

This project showcases a complete development workflow—from clean architecture and Gradle configuration to a polished, user-centric interface—making it an excellent reference for production-grade AR applications.

---

## ✨ Features

### 🎯 **Core Functionality**
- **Surface Detection & Object Placement** — Seamlessly detect real-world surfaces and place 3D objects with precision
- **Interactive 3D Objects** — Manipulate objects with intuitive touch gestures (move, rotate, scale)
- **Scene Management** — Clear all placed objects instantly with a single tap
- **Real-time Rendering** — Smooth 60 FPS AR experience with optimized performance

### ⚙️ **Customization**
- **Dynamic Settings** — Adjust cube size and selection behavior on-the-fly
- **Persistent Preferences** — User settings saved automatically using SharedPreferences
- **Intuitive UI** — Modern Material Design with clear visual feedback

### 🛡️ **Reliability**
- **Comprehensive Error Handling** — Graceful recovery from common AR exceptions
- **Permission Management** — Smart camera permission requests with user guidance
- **Quality Assurance** — Full test suite including unit and instrumentation tests
- **Device Compatibility** — Optimized for all ARCore-supported devices

---

## 🎬 Demo


![WhatsApp Image 2025-11-30 at 6 22 41 PM](https://github.com/user-attachments/assets/9ab1d812-9552-419e-89c3-67f6b2b716f2) ![WhatsApp Image 2025-11-30 at 6 22 43 PM](https://github.com/user-attachments/assets/a8e37606-7f39-4c48-afad-f03a1383a668) ![WhatsApp Image 2025-11-30 at 6 22 42 PM](https://github.com/user-attachments/assets/27905432-6087-47af-8dbe-71761a385118)


---

## 🏗️ Technical Architecture

### **Technology Stack**

```
┌─────────────────────────────────────┐
│         ARoute Application          │
├─────────────────────────────────────┤
│  UI Layer (Activities & Layouts)    │
├─────────────────────────────────────┤
│  Sceneform (3D Rendering Engine)    │
├─────────────────────────────────────┤
│  ARCore (AR Foundation)             │
├─────────────────────────────────────┤
│  Android SDK + Kotlin               │
└─────────────────────────────────────┘
```

### **Key Components**

| Component | Technology | Purpose |
|-----------|-----------|---------|
| **AR Engine** | ARCore | Real-world tracking and surface detection |
| **3D Rendering** | Sceneform | High-level 3D object management |
| **UI Framework** | ArFragment | Pre-built AR camera view and gesture controls |
| **Language** | Kotlin | Modern, concise Android development |
| **Build System** | Gradle KTS | Type-safe build configuration |

### **How It Works**

1. **ARCore** analyzes camera feed to understand the physical environment
2. **Plane Detection** identifies flat surfaces suitable for object placement
3. **User Interaction** triggers anchor creation at tap location
4. **Sceneform** renders 3D cube model attached to the anchor
5. **Gesture Recognition** enables real-time object manipulation

---

## 📁 Project Structure

```
ARoute/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/aroute/
│   │   │   │   ├── MainActivity.kt           # Main AR experience
│   │   │   │   └── SettingsActivity.kt       # User preferences
│   │   │   ├── res/
│   │   │   │   ├── layout/                   # XML layouts
│   │   │   │   ├── drawable/                 # UI assets
│   │   │   │   └── menu/                     # Action bar menu
│   │   │   └── AndroidManifest.xml           # App configuration
│   │   ├── test/                             # Unit tests
│   │   └── androidTest/                      # UI tests
│   └── build.gradle.kts                      # App-level config
├── build.gradle.kts                          # Project-level config
├── settings.gradle.kts                       # Module definitions
└── gradle.properties                         # Build properties
```

---

## 🚀 Installation

### **Prerequisites**

- **Android Studio** — Arctic Fox (2020.3.1) or later
- **Android SDK** — API Level 24+ (Android 7.0)
- **ARCore Compatible Device** — [Check compatibility](https://developers.google.com/ar/devices)
- **USB Debugging** — Enabled on your device

### **Setup Steps**

1. **Clone the Repository**
   ```bash
   git clone https://github.com/AION-2000/Aroute.git
   cd ARoute
   ```

2. **Open in Android Studio**
    - Launch Android Studio
    - Select "Open an Existing Project"
    - Navigate to the cloned directory

3. **Sync Dependencies**
   ```bash
   # Android Studio will prompt you to sync
   # Or manually: File → Sync Project with Gradle Files
   ```

4. **Connect Your Device**
    - Enable Developer Options and USB Debugging
    - Connect via USB cable
    - Accept any connection prompts

5. **Install & Run**
   ```bash
   ./gradlew installDebug
   ```
   Or click the **Run** button (▶️) in Android Studio

---

## 📖 Usage

### **Getting Started**

1. **Launch ARoute** from your app drawer
2. **Grant Camera Permission** when prompted (required for AR)
3. **Scan Your Environment** — Point camera at a flat, well-lit surface
4. **Wait for Detection** — Look for white plane indicators
5. **Place Objects** — Tap detected surfaces to place the cube
6. **Interact** — Drag to move, pinch to scale, two-finger rotate

### **Advanced Features**

| Action | Gesture | Result |
|--------|---------|--------|
| **Place Cube** | Single Tap | Creates new object at tap location |
| **Move Cube** | Drag (1 finger) | Repositions object on plane |
| **Rotate Cube** | Rotate (2 fingers) | Changes object orientation |
| **Scale Cube** | Pinch (2 fingers) | Adjusts object size |
| **Clear Scene** | Clear Button | Removes all placed objects |
| **Open Settings** | Menu Icon (⋮) | Access customization options |

### **Settings Options**

- **Cube Size** — Adjust initial size via slider (0.1m - 0.5m)
- **Auto-Select** — Toggle automatic selection of newly placed objects

---

## 🧪 Testing

### **Run Unit Tests**
```bash
./gradlew test
```

### **Run Instrumentation Tests**
```bash
./gradlew connectedAndroidTest
```

### **Test Coverage**
- Settings management logic
- UI component interactions
- App launch and initialization
- Permission handling flows

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

### **Contribution Guidelines**

- Follow Kotlin coding conventions
- Add tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting PR

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- **Google ARCore** — Powering the AR experience
- **Sceneform** — Simplifying 3D rendering
- **Android Community** — For continuous inspiration and support

---

<div align="center">

### ⭐ Star this repo if you find it helpful!

**Made with ❤️ by Shihab Shahriar Aion (https://github.com/yAION-2000)**
[Report Bug](https://github.com/AION-2000/aroute/issues) • [Request Feature](https://github.com/AION-2000/aroute/issues)

</div>
