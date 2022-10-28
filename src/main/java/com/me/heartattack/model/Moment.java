package com.me.heartattack.model;

import lombok.Builder;
import lombok.Value;
import org.openqa.selenium.WebElement;

@Value
@Builder
public class Moment {
    WebElement element;
    MomentType type;
    String tagName;
    Bound bound;
}
