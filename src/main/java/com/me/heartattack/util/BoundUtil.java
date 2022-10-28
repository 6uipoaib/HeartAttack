package com.me.heartattack.util;

import com.me.heartattack.model.Bound;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoundUtil {
    private static final Pattern pattern = Pattern.compile("\\[(\\d+),(\\d+)]\\[(\\d+),(\\d+)]");

    public static Bound getBound(final String bound) {
        final Matcher matcher = pattern.matcher(bound);
        if (matcher.find()) {
            return Bound.builder()
                    .x1(Integer.parseInt(matcher.group(1)))
                    .y1(Integer.parseInt(matcher.group(2)))
                    .x2(Integer.parseInt(matcher.group(3)))
                    .y2(Integer.parseInt(matcher.group(4)))
                    .build();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
