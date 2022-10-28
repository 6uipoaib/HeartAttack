package com.me.heartattack.like;

import com.me.heartattack.core.LikeStatusDetector;
import io.appium.java_client.android.AndroidDriver;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import javax.inject.Inject;

import static com.me.heartattack.util.XPathUtil.updateXPath;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class GeneralLiker implements Liker {

    private static final String COMMENT_BUTTON_XPATH = "//${IB}[@content-desc=\"Comment\"]";
    private static final String LIKE_BUTTON_XPATH = "//${IB}[@content-desc=\"Comment\"]/preceding-sibling::${LL}[last()]/${LL}[1]";

    private final AndroidDriver driver;
    private final LikeStatusDetector detector;

    @Override
    public boolean like() {
        final WebElement commentButton = driver.findElement(By.xpath(updateXPath(COMMENT_BUTTON_XPATH)));
        commentButton.click();

        final WebElement likeButton = driver.findElement(By.xpath(updateXPath(LIKE_BUTTON_XPATH)));
        final String base64Image = likeButton.getScreenshotAs(OutputType.BASE64);

        if (detector.isLiked(base64Image, "like_large.png")) {
            return false;
        } else {
            likeButton.click();
            return true;
        }
    }
}
