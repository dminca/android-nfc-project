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

## Useful resources
- [Build unsigned APK file on Android Studio][1]
- [Install parse failed no certificates when installing APK][2]
- [Sharing files with NFC on Android][3]
- [Android NFC read and write example][4]
- [jetruby/android-beam-nfc-example][5]

[1]: https://stackoverflow.com/questions/16709848/build-unsigned-apk-file-with-android-studio#16709979
[2]: https://stackoverflow.com/a/20878125/2733115
[3]: https://code.tutsplus.com/tutorials/sharing-files-with-nfc-on-android--cms-22501
[4]: http://www.codexpedia.com/android/android-nfc-read-and-write-example/
[5]: https://github.com/jetruby/android-beam-nfc-example