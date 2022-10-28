package com.me.heartattack.core;

import io.appium.java_client.android.AndroidDriver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import javax.inject.Inject;
import java.time.Duration;
import java.util.Collections;

import static com.me.heartattack.config.Config.SCROLL_FROM_PERCENTAGE_OF_HEIGHT;
import static com.me.heartattack.config.Config.SCROLL_TO_PERCENTAGE_OF_HEIGHT;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class Scroller {

    private final AndroidDriver driver;

    public void scrollDownABit() {
        log.info("Scroll down");
        final Dimension windowSize = driver.manage().window().getSize();
        final int midPoint = windowSize.getWidth() / 2;

        final int windowHeight = windowSize.getHeight();
        final int scrollFrom = windowHeight * SCROLL_FROM_PERCENTAGE_OF_HEIGHT / 100;
        final int scrollTo = windowHeight * SCROLL_TO_PERCENTAGE_OF_HEIGHT / 100;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), midPoint, scrollFrom));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        sequence.addAction(new Pause(finger, Duration.ofMillis(300)));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), midPoint, scrollTo));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }
}
