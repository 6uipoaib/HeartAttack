package com.me.heartattack.config;

import java.time.Duration;

public class Config {
    // Android device ID
    public static final String DEVICE_ID = "8ADX0QZ6E";

    // Appium server URL
    public static final String APPIUM_SERVER = "http://127.0.0.1:4723/wd/hub";

    // Sleep after entering moment detail page
    public static final long SLEEP_INTERVAL = Duration.ofSeconds(1).toMillis();

    // Scroll from position
    public static final int SCROLL_FROM_PERCENTAGE_OF_HEIGHT = 80;

    // Scroll to position
    public static final int SCROLL_TO_PERCENTAGE_OF_HEIGHT = 20;

    // Stop if X consecutive moments are already liked
    public static final int TERMINATION_AFTER_CONTINUES_NUM_OF_LIKED = 10;

    // If true, the script will keep running and like all unliked moments until the end
    public static final boolean NONE_STOP_MODE = false;

    // Save recording file recording-{timestamp}.mp4
    public static final boolean RECORDING = true;
}
