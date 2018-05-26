# Android NFC example
> Send a jpg file via NFC for the sake of example

## Prerequisites

For the app to function properly, you need to add a JPG file called `wallpaper.jpg` in the
`Downloads` directory.

## Requirements

- `adb` on Linux for installing the `apk`
- create a keystore for signing the APK
- sign the apk with the `apksigner` tool

### Generating the APK
On Android Studio, click on the
- **Select Run/Debug Configuration**
- **Edit configurations**
- **Add new configuration (ALT+INSERT)**
- **Gradle**
- Choose your Grandle project (select the project name from the dropdown)
- Add **Tasks: assemble**
- Run the build to generate APK

### Creating a keystore

On Linux issue the following:

```sh
keytool -genkey -v -keystore debug.keystore -alias android -keyalg RSA -keysize 2048 -validity 20000
```

### Signing the apk

You need to **find** the apk first, so on Linux:

```sh
cd /path/to/android-studio-project
find . -type f -name '*.apk'
```

After you found the path to your apk, sign it:

```sh
/opt/android-sdk/build-tools/27.0.3/apksigner sign --ks ~/Documents/debug.keystore ./app/build/outputs/apk/release/app-release-unsigned.apk
```

### Installing the APK to your Android device

- Plug-in your Android device via USB, turn **Debugging via USB** ON, and **Select USB configuration**
  to **PTP (Picture Transfer Protocol)**
- on your Linux machine, install the APK via `adb`:

```sh
adb install ./app/build/outputs/apk/release/app-release-unsigned.apk
```