package com.me.heartattack.like;

import com.me.heartattack.core.LikeStatusDetector;
import io.appium.java_client.android.AndroidDriver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class PhotoLiker implements Liker {

    private static final String LIKE_BUTTON_XPATH = "//android.widget.LinearLayout[contains(@content-desc,\"Like\")]/android.widget.ImageView";
    private final AndroidDriver driver;
    private final LikeStatusDetector detector;

    @Override
    public boolean like() {
        final WebElement likeButton = driver.findElement(By.xpath(LIKE_BUTTON_XPATH));
        final String base64Image = likeButton.getScreenshotAs(OutputType.BASE64);

        if (detector.isLiked(base64Image, "like_tiny.png")) {
            return false;
        } else {
            likeButton.click();
            return true;
        }
    }
}
