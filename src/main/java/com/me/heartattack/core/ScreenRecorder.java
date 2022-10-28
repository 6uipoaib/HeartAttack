package com.me.heartattack.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static com.me.heartattack.config.Config.RECORDING;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ScreenRecorder {

    private final AndroidDriver driver;

    public void start() {
        if (!RECORDING) {
            return;
        }

        log.info("Start recording...");
        driver.startRecordingScreen(
                AndroidStartScreenRecordingOptions.startScreenRecordingOptions()
        );
    }

    public void stopAndSave() {
        if (!RECORDING) {
            return;
        }

        log.info("Saving recording...");
        final String recording = driver.stopRecordingScreen();
        Path path = Paths.get(String.format("recording-%s.mp4", System.currentTimeMillis()));
        try {
            Files.write(path, Base64.getDecoder().decode(recording));
        } catch (IOException e) {
            log.info("Failed to save recording");
        }
        log.info("Recording saved as recording.mp4");
    }

}
