package com.me.heartattack.core;

import com.me.heartattack.like.Liker;
import com.me.heartattack.model.Moment;
import com.me.heartattack.model.MomentType;
import io.appium.java_client.android.AndroidDriver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.Map;

import static com.me.heartattack.config.Config.SLEEP_INTERVAL;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class LikeActionOrchestrator {

    private final AndroidDriver driver;
    private final Map<MomentType, Liker> momentTypeLiker;

    public boolean like(final Moment moment) {
        // Enter moment detail page
        moment.getElement().click();

        sleep();

        // Like!
        try {
            final boolean likeResult = momentTypeLiker.get(moment.getType()).like();
            if (!likeResult) {
                log.info(">> Already liked");
            } else {
                log.info(">> Just Liked!");
            }

            // Back from moment detail page
            driver.navigate().back();
            return likeResult;
        } catch (Exception e) {
            log.error("Failed to like moment: {}", moment.getTagName(), e);
            return false;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_INTERVAL);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
