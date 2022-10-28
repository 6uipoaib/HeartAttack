package com.me.heartattack.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class XPathUtil {
    private static final Map<String, String> REPLACE_MAP = Map.of(
            "${LL}", "android.widget.LinearLayout",
            "${FL}", "android.widget.FrameLayout",
            "${V}", "android.view.View",
            "${TV}", "android.widget.TextView",
            "${IB}", "android.widget.ImageButton"
    );

    public static String updateXPath(String path) {
        for (String key : REPLACE_MAP.keySet()) {
            path = StringUtils.replace(path, key, REPLACE_MAP.get(key));
        }
        return path;
    }
}
