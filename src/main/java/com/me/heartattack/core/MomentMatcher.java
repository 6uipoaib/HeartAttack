package com.me.heartattack.core;

import com.me.heartattack.model.Moment;
import com.me.heartattack.model.MomentType;
import io.appium.java_client.android.AndroidDriver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import javax.inject.Inject;
import java.util.Map;
import java.util.stream.Stream;

import static com.me.heartattack.util.BoundUtil.getBound;
import static com.me.heartattack.util.XPathUtil.updateXPath;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MomentMatcher {

    private final Map<MomentType, String> momentTypeToXpath;
    private final AndroidDriver driver;

    public Stream<Moment> match(final MomentType type) {
        try {
            return driver.findElements(By.xpath(updateXPath(momentTypeToXpath.get(type)))).stream()
                    .map(e -> Moment.builder()
                            .element(e)
                            .tagName(StringUtils.normalizeSpace(e.getTagName()))
                            .bound(getBound(e.getAttribute("bounds")))
                            .type(type)
                            .build());
        } catch (Exception e) {
            log.info("Failed to find moment type {} by XPATH", type);
            return Stream.of();
        }
    }
}
