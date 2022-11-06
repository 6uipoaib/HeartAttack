### Prerequisites
1. Android phone with developer mode enabled. IOS devices are not supported.
1. Install [adb](https://developer.android.com/studio/command-line/adb) and add it to `$PATH`.
1. Install Appium Server. GUI distributions([link](https://github.com/appium/appium-desktop/releases)) are recommended.

### How to run
1. Open WeChat to user moment page. Put device to "Do Not Disturb" mode.
1. In terminal, run `adb devices` to get Android phone deviceId.
1. Start Appium server.
1. Update `Config.java`, replace the `DEVICE_ID` and `APPIUM_SERVER` path.
1. Run `HeartAttack.main()` from your IDE. 

### Proof that it works on my phone
![](https://raw.githubusercontent.com/6uipoaib/HeartAttack/master/gif/sample.gif)

### Disclaimer
Just for fun. Only tested on my Pixel 3. No guarantees. Feel free to submit issues or do anything you want with it.