package com.me.heartattack.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Bound {
    int x1;
    int y1;
    int x2;
    int y2;
    int yCenter;

    public int getYCenter() {
        return (y1 + y2) / 2;
    }
}
